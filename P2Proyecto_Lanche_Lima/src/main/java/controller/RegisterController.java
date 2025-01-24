package controller;

import model.connectionMongoDB;
import model.Client;
import model.Rooms;
import view.RegisterInterfaz;
import view.LoginInterfaz;
import view.OptionsRoomsInterfaz;
import view.DetailRoomInterfaz;
import view.CheckFinishStepInterfaz;
import controller.CheckFinishStepController;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import org.bson.Document;

public class RegisterController implements ActionListener {

    protected LoginInterfaz viewLogin;
    protected RegisterInterfaz viewRegister;
    protected LoginController logCon;
    protected OptionsRoomsInterfaz optionsRoomsInterfaz;
    protected DetailRoomInterfaz detailRoomInterfaz;
    protected CheckFinishStepController checkFinishStepController;
    protected Client modelClient;
    protected Rooms rooms;
    private connectionMongoDB mongo = new connectionMongoDB();

    public RegisterController(LoginInterfaz viewLogin, RegisterInterfaz viewRegister, Client modelClient, OptionsRoomsInterfaz optionsRoomsInterfaz, DetailRoomInterfaz detailRoomInterfaz, Rooms rooms) {
        this.viewLogin = viewLogin;
        this.viewRegister = viewRegister;
        this.optionsRoomsInterfaz = optionsRoomsInterfaz;
        this.detailRoomInterfaz = detailRoomInterfaz;
        this.rooms = rooms;
        this.modelClient = modelClient;
        mongo.createConnection();
        this.viewRegister.btnSignUp.addActionListener(this);
        this.viewRegister.btnSignIn.addActionListener(this);
    }

    public void iniciarView() {
        System.out.println("SI");
        viewRegister.setVisible(true);
        limpiarValidadores();
    }

    public void guardarDatos() {
        modelClient = new Client(
                viewRegister.tfUsername.getText(),
                String.copyValueOf(viewRegister.pfPassword.getPassword()),
                new ArrayList<>(),
                viewRegister.tfFullName.getText(),
                viewRegister.tfEmail.getText(),
                viewRegister.tfAddress.getText(),
                viewRegister.tfPhone.getText()
        );
        modelClient.login();
        CheckFinishStepInterfaz checkFinishStepInterfaz = new CheckFinishStepInterfaz();
        checkFinishStepController = new CheckFinishStepController(rooms, modelClient, true, checkFinishStepInterfaz);
        checkFinishStepController.iniciarView();

        viewRegister.dispose();
    }

    public boolean thereAreNumbers(String varComprobate, boolean numOrText) {
        if (numOrText) {
            return varComprobate.matches("\\d+");
        }
        return varComprobate.matches(".*\\d.*");
    }

    public boolean newUser(String str, Object obj) {
        Document filtro = new Document(str, obj);
        ArrayList<Document> resultados = new ArrayList<>(mongo.searchDocument(filtro));
        return resultados.isEmpty();
    }

    public boolean validateUserInformation() {
        boolean allGood = true, passwordAllGood = true;

        // validacion nombres
        if (viewRegister.tfFullName.getText().isBlank()) {
            viewRegister.lbErrorFullName.setText("Error, ingrese su nombre");
            viewRegister.lbErrorFullName.setForeground(Color.red);
            allGood = false;
        } else if (thereAreNumbers(viewRegister.tfFullName.getText(), false)) {
            viewRegister.lbErrorFullName.setText("Error, ingrese solo letras");
            viewRegister.lbErrorFullName.setForeground(Color.red);
            allGood = false;
        } else {
            viewRegister.lbErrorFullName.setText(" ");
        }

        // validacion contraseña
        if (String.copyValueOf(viewRegister.pfPassword.getPassword()).isBlank()) {
            viewRegister.lbErrorPassword.setText("Error, ingrese una contraseña");
            viewRegister.lbErrorPassword.setForeground(Color.red);
            allGood = false;
            passwordAllGood = false;
        } else if (String.copyValueOf(viewRegister.pfPassword.getPassword()).length() < 8 || String.copyValueOf(viewRegister.pfPassword.getPassword()).length() > 30) {
            viewRegister.lbErrorPassword.setText("Error, ingrese entre 8 y 30 caracteres");
            viewRegister.lbErrorPassword.setForeground(Color.red);
            allGood = false;
            passwordAllGood = false;
        } else {
            viewRegister.lbErrorPassword.setText(" ");
        }

        if (!passwordAllGood) {
            viewRegister.lbErrorConfirmPassword.setText("Error, primero agregue una contraseña válida");
            viewRegister.lbErrorConfirmPassword.setForeground(Color.red);
            allGood = false;
        } else if (String.copyValueOf(viewRegister.pfConfirmPassword.getPassword()).isBlank()) {
            viewRegister.lbErrorConfirmPassword.setText("Error, debe confirmar su contraseña");
            viewRegister.lbErrorConfirmPassword.setForeground(Color.red);
            allGood = false;
        } else if (!String.copyValueOf(viewRegister.pfConfirmPassword.getPassword()).equals(String.copyValueOf(viewRegister.pfPassword.getPassword()))) {
            viewRegister.lbErrorConfirmPassword.setText("Error, las contraseñas no coinciden");
            viewRegister.lbErrorConfirmPassword.setForeground(Color.red);
            allGood = false;
        } else {
            viewRegister.lbErrorConfirmPassword.setText(" ");
        }

        // validacion direccion
        if (!viewRegister.tfAddress.getText().matches("^[a-zA-Z][a-zA-Z0-9 ]+$")) {
            viewRegister.lbErrorAddress.setText("Error, ingrese una dirección válida");
            viewRegister.lbErrorAddress.setForeground(Color.red);
            allGood = false;
        } else {
            viewRegister.lbErrorAddress.setText(" ");
        }

        // validacion nombre de usuario
        if (!viewRegister.tfUsername.getText().matches("^[a-zA-Z][a-zA-Z0-9]+$")) {
            viewRegister.lbErrorUsername.setText("Error, usuario incorrecto");
            viewRegister.lbErrorUsername.setForeground(Color.red);
            allGood = false;
        } else if (!newUser("Username", viewRegister.tfUsername.getText())) {
            viewRegister.lbErrorUsername.setText("Error, usuario ya registrado");
            viewRegister.lbErrorUsername.setForeground(Color.red);
            allGood = false;
        } else {
            viewRegister.lbErrorUsername.setText(" ");
        }

        // validacion correo
        if (viewRegister.tfEmail.getText().isBlank()) {
            viewRegister.lbErrorEmail.setText("Error, ingrese su correo");
            viewRegister.lbErrorEmail.setForeground(Color.red);
            allGood = false;
        } else if (!viewRegister.tfEmail.getText().matches("^(?!.*--)[a-zA-Z0-9]+(-[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(\\.[a-zA-Z]{2,})+$")) {
            viewRegister.lbErrorEmail.setText("Error, correo no válido");
            viewRegister.lbErrorEmail.setForeground(Color.red);
            allGood = false;
        } else if (!newUser("Email", viewRegister.tfEmail.getText())) {
            viewRegister.lbErrorEmail.setText("Error, correo ya registrado");
            viewRegister.lbErrorEmail.setForeground(Color.red);
            allGood = false;
        } else {
            viewRegister.lbErrorEmail.setText(" ");
        }

        // validacion telefono
        if (viewRegister.tfPhone.getText().isBlank()) {
            viewRegister.lbErrorPhone.setText("Error, ingrese su teléfono");
            viewRegister.lbErrorPhone.setForeground(Color.red);
            allGood = false;
        } else if (!thereAreNumbers(viewRegister.tfPhone.getText(), true)) {
            viewRegister.lbErrorPhone.setText("Error, solo números");
            viewRegister.lbErrorPhone.setForeground(Color.red);
            allGood = false;
        } else if (viewRegister.tfPhone.getText().length() != 10) {
            viewRegister.lbErrorPhone.setText("Error, ingrese 10 números");
            viewRegister.lbErrorPhone.setForeground(Color.red);
            allGood = false;
        } else if (!newUser("Phone", viewRegister.tfPhone.getText())) {
            viewRegister.lbErrorPhone.setText("Error, teléfono ya registrado");
            viewRegister.lbErrorPhone.setForeground(Color.red);
            allGood = false;
        } else {
            viewRegister.lbErrorPhone.setText(" ");
        }

        return allGood;
    }

    public void limpiarValidadores() {
        viewRegister.lbErrorFullName.setText(" ");
        viewRegister.lbErrorUsername.setText(" ");
        viewRegister.lbErrorEmail.setText(" ");
        viewRegister.lbErrorPassword.setText(" ");
        viewRegister.lbErrorConfirmPassword.setText(" ");
        viewRegister.lbErrorAddress.setText(" ");
        viewRegister.lbErrorPhone.setText(" ");
    }

    public void signIn() {
        LoginInterfaz li = new LoginInterfaz();
        Client clientForNow = new Client("", "", null, "", "", "", "");
        LoginController loginController = new LoginController(li, viewRegister, clientForNow, rooms);
        loginController.iniciarView();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewRegister.btnSignUp) {
            if (validateUserInformation()) {
                guardarDatos();
            }
        } else if (e.getSource() == viewRegister.btnSignIn) {
            signIn();
            viewRegister.dispose();
        }
    }
}
