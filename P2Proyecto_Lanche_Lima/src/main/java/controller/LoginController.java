package controller;

import model.connectionMongoDB;
import model.Client;
import view.LoginInterfaz;
import view.RegisterInterfaz;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.bson.Document;

public class LoginController implements ActionListener {

    //DECLARACIÓN DE ATRIBUTOS
    protected RegisterController regCon;
    protected ClientController cliCon;
    protected LoginInterfaz viewLogin;
    protected RegisterInterfaz viewRegister;
    protected ClientInterfaz viewClient;
    protected Client modelClients;
    private connectionMongoDB mongo = new ConnectionDBLima();

    //CONSTRUCTOR
    public LoginController(LoginInterfaz viewLogin, RegisterInterfaz viewRegister, Clients modelClients, Workers modelWorkers, RegisterController regCon, ClientController cliCon, ClientInterfaz viewClient) {
        this.viewClient = viewClient;
        this.cliCon = cliCon;
        this.viewRegister = viewRegister;
        this.regCon = regCon;
        this.viewLogin = viewLogin;
        this.modelClients = modelClients;
        this.modelWorkers = modelWorkers;
        mongo.createConnection();//realizar la conexión con mongo

        //ESTABLECER LISTENERS A LOS BOTONES DE LA INTERFAZ
        this.viewLogin.btnSignUp.addActionListener(this);
        this.viewLogin.btnSignIn.addActionListener(this);
        this.viewLogin.btnForgotPassword.addActionListener(this);
    }

    public void setModelClients(Clients modelClients) {
        this.modelClients = modelClients;
    }

    public void setModelWorkers(Workers modelWorkers) {
        this.modelWorkers = modelWorkers;
    }

    //MÉTODO PARA INICIAR LA VISTA
    public void iniciarView() {
        viewLogin.setVisible(true);
        validatorsCleaner();
    }

    public void validatorsCleaner() {
        viewLogin.lbErrorUsername.setText(" ");
        viewLogin.lbErrorPassword.setText(" ");
    }

    public boolean validationPassword(boolean isWorker) {
        boolean allGood = true;
        //VALIDACIÓN DEL PASSWORD
        if (String.copyValueOf(viewLogin.pfPassword.getPassword()).isBlank()) {
            viewLogin.lbErrorPassword.setText("Error, ingrese una contraseña");
            viewLogin.lbErrorPassword.setForeground(Color.red);
            allGood = false;
        } else if (String.copyValueOf(viewLogin.pfPassword.getPassword()).length() < 8 || String.copyValueOf(viewLogin.pfPassword.getPassword()).length() > 30) {
            viewLogin.lbErrorPassword.setText("Error, ingrese entre 8 y 30 caracteres");
            viewLogin.lbErrorPassword.setForeground(Color.red);
            allGood = false;
        } else {
            if (isWorker) {//si es trabajador se valida con el model del trabajador
                if (String.copyValueOf(viewLogin.pfPassword.getPassword()).equals(modelWorkers.getPassword())) {
                    viewLogin.lbErrorPassword.setText(" ");
                } else {
                    viewLogin.lbErrorPassword.setText("Error, la contraseña es incorrecta");
                    viewLogin.lbErrorPassword.setForeground(Color.red);
                    allGood = false;
                }
            } else {//Si no es trabajador se valida con el modelo del cliente
                if (String.copyValueOf(viewLogin.pfPassword.getPassword()).equals(modelClients.getPassword())) {
                    viewLogin.lbErrorPassword.setText(" ");
                } else {
                    viewLogin.lbErrorPassword.setText("Error, la contraseña es incorrecta");
                    viewLogin.lbErrorPassword.setForeground(Color.red);
                    allGood = false;
                }
            }
        }
        return allGood;
    }

    public boolean validationUser() {
        boolean allGood = true;
        //VALIDACIÓN DEL USERNAME
        if (!viewLogin.tfUsername.getText().matches("^[a-zA-Z][a-zA-Z0-9]+$")/*Valida del usuario lo siguiente:
                - No puede comenzar con un número.
                - Puede contener solo letras y números.
                - No puede ser solo un número.
                - La cadena no debe estar vacía.*/) {
            viewLogin.lbErrorUsername.setText("Error, usuario incorrecto");
            viewLogin.lbErrorUsername.setForeground(Color.red);
            allGood = false;
        } else {
            viewLogin.lbErrorUsername.setText(" ");

            Document filtro = new Document("Username", viewLogin.tfUsername.getText());
            ArrayList<Document> resultados;
            boolean continueSearch = false;
            do {
                resultados = mongo.searchSelector(filtro, continueSearch);
                if (!resultados.isEmpty()) {
                    Document doc = resultados.get(0); // Obtener el primer documento
                    if (continueSearch) {
                        setModelWorkers(new Workers(doc.getString("Username"), doc.getString("Password"), doc.getString("Profile"), doc.getString("Email"), doc.getString("Phone"), doc.getString("FullName"), doc.getString("IdCard")));
                    } else {
                        setModelClients(new Clients(doc.getString("Username"), doc.getString("Password"), doc.getString("Profile"), doc.getString("Email"), doc.getString("Phone"), doc.getString("FullName"), doc.getString("Addres"), null));
                    }
                    allGood = validationPassword(continueSearch);
                    System.out.println("Username" + " encontrado: " + doc.getString("Username") + ". En " + resultados.size() + " resultados.");
                    viewLogin.lbErrorUsername.setText(" ");
                    return allGood;
                } else {
                    System.out.println("\n\n----->No se encontraron resultados.<-----\n\n");
                    viewLogin.lbErrorUsername.setText("Error, el usuario no existe");
                    viewLogin.lbErrorUsername.setForeground(Color.red);
                    continueSearch = !continueSearch;
                }
            } while (continueSearch);
            allGood = continueSearch;
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
                        resultados = mongo.searchSelector(filtro, continueSearch);
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
                        resultados = mongo.searchSelector(filtro, continueSearch);
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
        if (e.getSource() == viewLogin.btnSignUp) {//para cuando presione el botón de "registrarse"
            modelClients = new Clients("", "", "", "", "", "", "", null);
            viewRegister = new RegisterInterfaz();
            regCon = new RegisterController(viewRegister, modelClients, false, null, null);

            regCon.iniciarView();
            viewLogin.setVisible(false);
        } else if (e.getSource() == viewLogin.btnSignIn) {//botón de iniciar sesión
            if (validationUser()) {
                viewClient = new ClientInterfaz();
                cliCon = new ClientController(viewClient);//instancia del controlador de la interfaz del cliente

                cliCon.iniciarView();//se inicia el controlador
                viewLogin.setVisible(false);//se cierra la ventana del login
            }
        } else if (e.getSource() == viewLogin.btnForgotPassword) {
            validationGiveCredentials();
            validatorsCleaner();
            viewLogin.tfUsername.setText("");
            viewLogin.pfPassword.setText("");
        }
    }
}
