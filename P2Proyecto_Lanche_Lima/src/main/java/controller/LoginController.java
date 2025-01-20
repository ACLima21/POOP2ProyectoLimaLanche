package controller;

import view.LoginInterfaz;
import view.RegisterInterfaz;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class LoginController implements ActionListener {

    protected LoginInterfaz loginInt;
    private JLabel lbErrorUsername;
    private JLabel lbErrorPassword;

    public LoginController(LoginInterfaz loginInt) {
        this.loginInt = loginInt;
        this.lbErrorUsername = loginInt.lbErrorUsername;
        this.lbErrorPassword = loginInt.lbErrorPassword;
        this.loginInt.btnSignIn.addActionListener(this);
        this.loginInt.btnSignUp.addActionListener(this);
    }

    public void startView() {
        loginInt.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginInt.btnSignIn) {
            String username = loginInt.tfUsername.getText();
            char[] passwordArray = loginInt.pfPassword.getPassword();
            String password = new String(passwordArray);

            boolean isValid = true;

            if (username.isEmpty()) {
                lbErrorUsername.setText("Campo obligatorio");
                isValid = false;
            } else {
                lbErrorUsername.setText("");
            }

            if (passwordArray.length == 0) {
                lbErrorPassword.setText("Campo obligatorio");
                isValid = false;
            } else {
                lbErrorPassword.setText("");
            }

            if (isValid) {
                System.out.println("Iniciando sesión...");
            }

            Arrays.fill(passwordArray, '\0');
        } else if (e.getSource() == loginInt.btnSignUp) {
            RegisterInterfaz registerInt = new RegisterInterfaz();
            registerInt.setVisible(true);
            loginInt.dispose();
        }
    }
}
