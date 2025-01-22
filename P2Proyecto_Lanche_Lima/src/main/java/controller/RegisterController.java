package controller;

import model.ConnectionDBLima;
import model.Clients;
import view.RegisterInterfaz;
import view.LoginInterfaz;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import org.bson.Document;

public class RegisterController implements ActionListener {

    protected LoginController logCon;
    protected LoginInterfaz viewLogin;
    protected RegisterInterfaz viewRegister;
    protected Clients modelClients;
    protected boolean isAdmin;
    private ConnectionDBLima mongo = new ConnectionDBLima();

    public RegisterController(RegisterInterfaz viewRegister, Clients modelClients, boolean isAdmin, LoginController logCon, LoginInterfaz viewLogin) {
        this.viewLogin = viewLogin;
        this.logCon = logCon;
        this.viewRegister = viewRegister;
        this.modelClients = modelClients;
        mongo.createConnection();
        this.viewRegister.btnSignUp.addActionListener(this);
        this.viewRegister.btnSignIn.addActionListener(this);
        this.isAdmin = isAdmin;
    }

    public void iniciarView() {
        viewRegister.setVisible(true);
        viewRegister.cbProfile.setEnabled(isAdmin);
        if (!isAdmin) {
            viewRegister.cbProfile.setSelectedIndex(1);
        }
        limpiarValidadores();
    }

    public void guardarDatos() {
        modelClients = new Clients("", "", "", "", "", "", "", null);

        modelClients.setUsername(viewRegister.tfUsername.getText());
        modelClients.setPassword(String.copyValueOf(viewRegister.pfPassword.getPassword()));
        modelClients.setProfile(viewRegister.cbProfile.getSelectedItem().toString());
        modelClients.setEmail(viewRegister.tfEmail.getText());
        modelClients.setPhone(viewRegister.tfPhone.getText());
        modelClients.setFullName(viewRegister.tfFullName.getText());
        modelClients.setAddres(viewRegister.tfAddress.getText());

        Document doc = new Document("Username", modelClients.getUsername())
                .append("Password", modelClients.getPassword())
                .append("Profile", modelClients.getProfile())
                .append("Email", modelClients.getEmail())
                .append("Phone", modelClients.getPhone())
                .append("FullName", modelClients.getFullName())
                .append("Addres", modelClients.getAddres())
                .append("Reservations", modelClients.getReser());
        mongo.createSelector(doc, isAdmin);
        modelClients.login();

    }

    public boolean thereAreNumbers(String varComprobate, boolean numOrText) {
        if (numOrText) {
            return varComprobate.matches("\\d+")/*Comprueba si todos son números*/;
        }
        return varComprobate.matches(".*\\d.*");//Comprueba si hay almenos un número en la cadena
    }

    public boolean newUser(String str, Object obj) {
        Document filtro = new Document(str, obj);
        ArrayList<Document> resultados = mongo.searchSelector(filtro, isAdmin);
        if (!resultados.isEmpty()) {
            Document doc = resultados.get(0); // Obtener el primer documento
            String soughtValue = doc.getString(str); // Acceder al valor de
            System.out.println(str + " encontrado: " + soughtValue);
            return false;
        } else {
            System.out.println("\n\n----->No se encontraron resultados.<-----\n\n");
        }
        return true;
    }

    public boolean validateUserInformation() {
        boolean allGood = true, passwordAllGood = true;

        //VALIDACIÓN DE LOS NOMBRES COMPLETOS
        if (viewRegister.tfFullName.getText().isBlank()) {
            viewRegister.lbErrorFullName.setText("Error, ingrese su nombre");
            viewRegister.lbErrorFullName.setForeground(Color.red);
            allGood = false;
        } else if (thereAreNumbers(viewRegister.lbErrorFullName.getText(), false)) {
            viewRegister.lbErrorFullName.setText("Error, ingrese solo letras");
            viewRegister.lbErrorFullName.setForeground(Color.red);
            allGood = false;
        } else if (!newUser("FullName", viewRegister.lbErrorFullName.getText())) {
            viewRegister.lbErrorFullName.setText("Error, ese nombre ya está registrado");
            viewRegister.lbErrorFullName.setForeground(Color.red);
            allGood = false;
        } else {
            viewRegister.lbErrorFullName.setText(" ");
        }

        //VALIDACIÓN DE LA CONTRASEÑA
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

        //VALIDACIÓN DE LA DIRECCIÓN
        if (!viewRegister.tfAddress.getText().matches("^[a-zA-Z][a-zA-Z0-9 ]+$")/*Valida que:
                -Comience con una letra (mayúscula o minúscula).
                -Puede contener letras, números y espacios.
                -No puede comenzar con números ni con caracteres especiales.
                -No puede ser solo números.*/) {
            viewRegister.lbErrorAddress.setText("Error, ingrese una dirección válida");
            viewRegister.lbErrorAddress.setForeground(Color.red);
            allGood = false;
        } else {
            viewRegister.lbErrorAddress.setText(" ");
        }

        //VALIDACIÓN DEL USERNAME
        if (!viewRegister.tfUsername.getText().matches("^[a-zA-Z][a-zA-Z0-9]+$")/*Valida del usuario lo siguiente:
                - No puede comenzar con un número.
                - Puede contener solo letras y números.
                - No puede ser solo un número.
                - La cadena no debe estar vacía.*/) {
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

        //VALIDACIÓN DEL CORREO
        if (viewRegister.tfEmail.getText().isBlank()) {
            viewRegister.lbErrorEmail.setText("Error, ingrese su correo");
            viewRegister.lbErrorEmail.setForeground(Color.red);
            allGood = false;
        } else if (!viewRegister.tfEmail.getText().matches("^(?!.*--)[a-zA-Z0-9]+(-[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(\\.[a-zA-Z]{2,})+$")/*código regex que valida la entrada del correo*/) {
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

        //VALIDACIÓN DEL TELÉFONO
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
        } else {
            viewRegister.lbErrorPhone.setText(" ");
        }

        return allGood;
    }

    public void limpiarValidadores() {
        viewRegister.lbErrorAddress.setText(" ");
        viewRegister.lbErrorConfirmPassword.setText(" ");
        viewRegister.lbErrorEmail.setText(" ");
        viewRegister.lbErrorFullName.setText(" ");
        viewRegister.lbErrorPassword.setText(" ");
        viewRegister.lbErrorPhone.setText(" ");
        viewRegister.lbErrorProfile.setText(" ");
        viewRegister.lbErrorUsername.setText(" ");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewRegister.btnSignUp) {
            if (validateUserInformation()) {
                guardarDatos();
            }
        } else if (e.getSource() == viewRegister.btnSignIn) {
            viewLogin = new LoginInterfaz();
            logCon = new LoginController(viewLogin, null, null, null, null, null, null);

            logCon.iniciarView();
            viewRegister.setVisible(false);
        }
    }
}
