package controller;

import model.connectionMongoDB;
import model.Client;
import model.Rooms;
import view.LoginInterfaz;
import view.RegisterInterfaz;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.bson.Document;

public class LoginController implements ActionListener {

    protected RegisterController regCon;
    protected LoginInterfaz viewLogin;
    protected RegisterInterfaz viewRegister;
    protected Client modelClient;
    protected Rooms rooms;
    private connectionMongoDB mongo = new connectionMongoDB();

    public LoginController(RegisterInterfaz viewRegister, Rooms rooms) {
        this.rooms = rooms;
        this.viewLogin = viewLogin;
        this.viewRegister = viewRegister;
        mongo.createConnection();

        this.viewLogin.btnSignUp.addActionListener(this);
        this.viewLogin.btnSignIn.addActionListener(this);
    }

    public void setModelClient(Client modelClient) {
        this.modelClient = modelClient;
    }

    public void iniciarView() {
        viewLogin.setVisible(true);
        validatorsCleaner();
    }

    public void validatorsCleaner() {
        viewLogin.lbErrorUsername.setText(" ");
        viewLogin.lbErrorPassword.setText(" ");
    }

    public boolean validationPassword() {
        boolean allGood = true;
        // validacion contraseña
        if (String.copyValueOf(viewLogin.pfPassword.getPassword()).isBlank()) {
            viewLogin.lbErrorPassword.setText("Error, ingrese una contraseña");
            viewLogin.lbErrorPassword.setForeground(Color.red);
            allGood = false;
        } else if (String.copyValueOf(viewLogin.pfPassword.getPassword()).length() < 8 || String.copyValueOf(viewLogin.pfPassword.getPassword()).length() > 30) {
            viewLogin.lbErrorPassword.setText("Error, ingrese entre 8 y 30 caracteres");
            viewLogin.lbErrorPassword.setForeground(Color.red);
            allGood = false;
        } else {
            if (String.copyValueOf(viewLogin.pfPassword.getPassword()).equals(modelClient.getPassword())) {
                viewLogin.lbErrorPassword.setText(" ");
            } else {
                viewLogin.lbErrorPassword.setText("Error, la contraseña es incorrecta");
                viewLogin.lbErrorPassword.setForeground(Color.red);
                allGood = false;
            }
        }
        return allGood;
    }

    public boolean validationUser() {
        boolean allGood = true;
        // validacion usuario
        if (!viewLogin.tfUsername.getText().matches("^[a-zA-Z][a-zA-Z0-9]+$")) {
            viewLogin.lbErrorUsername.setText("Error, usuario incorrecto");
            viewLogin.lbErrorUsername.setForeground(Color.red);
            allGood = false;
        } else {
            viewLogin.lbErrorUsername.setText(" ");

            Document filtro = new Document("Username", viewLogin.tfUsername.getText());
            ArrayList<Document> resultados = new ArrayList<>(mongo.searchSelector(filtro));
            if (!resultados.isEmpty()) {
                Document doc = resultados.get(0); // Obtener el primer documento
                setModelClient(new Client(doc.getString("Username"), doc.getString("Password"), new ArrayList<>(), doc.getString("FullName"), doc.getString("Email"), doc.getString("Address"), doc.getString("Phone")));
                allGood = validationPassword();
                System.out.println("Username encontrado: " + doc.getString("Username") + ". En " + resultados.size() + " resultados.");
                viewLogin.lbErrorUsername.setText(" ");
                return allGood;
            } else {
                System.out.println("\n\n----->No se encontraron resultados.<-----\n\n");
                viewLogin.lbErrorUsername.setText("Error, el usuario no existe");
                viewLogin.lbErrorUsername.setForeground(Color.red);
            }
        }
        return allGood;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewLogin.btnSignUp) {
            if (viewRegister == null) {
                viewRegister = new RegisterInterfaz();
//Client modelClient, LoginController logCon, OptionsRoomsInterfaz optionsRoomsInterfaz, DetailRoomInterfaz detailRoomInterfaz, Rooms rooms
                regCon = new RegisterController(new Client("", "", new ArrayList<>(), "", "", "", ""), this, null, null, null);
            }
            regCon.iniciarView();
            viewLogin.setVisible(false);
        } else if (e.getSource() == viewLogin.btnSignIn) {
            if (validationUser()) {
                JOptionPane.showMessageDialog(viewLogin, "¡Inicio de sesión exitoso!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                viewLogin.setVisible(false);
            }
        }
    }
}
