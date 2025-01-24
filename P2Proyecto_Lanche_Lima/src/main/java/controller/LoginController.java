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
        this.viewLogin.btnForgotPassword.addActionListener(this);
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
            ArrayList<Document> resultados = new ArrayList<>(mongo.searchDocument(filtro));
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

    public boolean validationGiveCredentials() {
        ArrayList<Document> resultados;
        Document filtro;
        boolean giveIt = true, continueSearch;
        String[] options = {"Email", "Teléfono"};
        int optionIndex = JOptionPane.showOptionDialog(viewLogin, "Escoja como desea recuperar la contraseña\nSi no funciona ninguna de las opciones póngase en contacto con algún administrador", "Recuperar Contraseña", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        switch (optionIndex) {
            case 0://Email
                String emailToCheck = JOptionPane.showInputDialog(viewLogin, "Ingrese su email para recuperar sus credenciales:", "Recuperar con email", JOptionPane.QUESTION_MESSAGE);
                if (emailToCheck != null) {
                    filtro = new Document("Email", emailToCheck);
                    continueSearch = false;
                    do {
                        giveIt = true;
                        resultados = mongo.searchDocument(filtro);
                        if (!resultados.isEmpty()) {
                            Document doc = resultados.get(0); // Obtener el primer documento
                            JOptionPane.showMessageDialog(viewLogin, "Usted ha solicitado sus credenciales via email,\nsus credenciales son:\nUsername: " + doc.getString("Username") + "\nPassword: " + doc.getString("Password"), "CORREO RECIBIDO", JOptionPane.INFORMATION_MESSAGE);
                            System.out.println("Email encontrado: " + doc.getString("Email") + ". En " + resultados.size() + " resultados.");
                            return giveIt;
                        } else {
                            System.out.println("\n\n----->No se encontraron resultados.<-----\n\n");
                            giveIt = false;
                            continueSearch = !continueSearch;
                        }
                    } while (continueSearch);

                    if (!giveIt) {
                        JOptionPane.showMessageDialog(viewLogin, "El correo ingresado no existe en nuestra base de datos, intente\nde nuevo por favor.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    giveIt = false;
                }

                break;
            case 1://Telefono
                String phoneToCheck = JOptionPane.showInputDialog(viewLogin, "Ingrese su número de teléfono para recuperar sus credenciales:", "Recuperar con Teléfono", JOptionPane.QUESTION_MESSAGE);

                if (phoneToCheck != null) {
                    filtro = new Document("Phone", phoneToCheck);
                    continueSearch = false;
                    do {
                        giveIt = true;
                        resultados = mongo.searchDocument(filtro);
                        if (!resultados.isEmpty()) {
                            Document doc = resultados.get(0); // Obtener el primer documento
                            JOptionPane.showMessageDialog(viewLogin, "Usted ha solicitado sus credenciales via sms,\nsus credenciales son:\nUsername: " + doc.getString("Username") + "\nPassword: " + doc.getString("Password"), "MENSAJE DE TEXTO", JOptionPane.INFORMATION_MESSAGE);
                            System.out.println("Teléfono encontrado: " + doc.getString("Phone") + ". En " + resultados.size() + " resultados.");
                            return giveIt;
                        } else {
                            System.out.println("\n\n----->No se encontraron resultados.<-----\n\n");
                            giveIt = false;
                            continueSearch = !continueSearch;
                        }
                    } while (continueSearch);

                    if (!giveIt) {
                        JOptionPane.showMessageDialog(viewLogin, "El teléfono ingresado no existe en nuestra base de datos, intente\nde nuevo por favor.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    giveIt = false;
                }
                break;
            case -1://nada
                System.out.println("\n\nNO SE SELECCIONÓ NADA\n\n");
                giveIt = false;
                break;
        }
        return giveIt;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewLogin.btnSignUp) {
            if (viewRegister == null) {
                viewRegister = new RegisterInterfaz();
//Client modelClient, LoginController logCon, OptionsRoomsInterfaz optionsRoomsInterfaz, DetailRoomInterfaz detailRoomInterfaz, Rooms rooms
                regCon = new RegisterController(viewLogin, viewRegister, new Client("", "", new ArrayList<>(), "", "", "", ""), null, null, null);
            }
            regCon.iniciarView();
            System.out.println("Se inició la view");
            viewLogin.setVisible(false);
        } else if (e.getSource() == viewLogin.btnSignIn) {
            if (validationUser()) {
                modelClient.login();
                viewLogin.setVisible(false);
            }
        } else if (e.getSource() == viewLogin.btnForgotPassword) {
            validationGiveCredentials();
            validatorsCleaner();
            viewLogin.tfUsername.setText("");
            viewLogin.pfPassword.setText("");
        }
    }
}
