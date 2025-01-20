package controller;

import view.LoginInterfaz;
import view.RegisterInterfaz;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {

    protected LoginInterfaz loginInt;
 
    public LoginController(LoginInterfaz loginInt) {
        this.loginInt = loginInt;
        this.loginInt.btnSignUp.addActionListener(this);
    }

    public void startView() {
        loginInt.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginInt.btnSignUp) {
            RegisterInterfaz registerInt = new RegisterInterfaz();
            registerInt.setVisible(true);
            loginInt.dispose(); 
        }
    }
}
