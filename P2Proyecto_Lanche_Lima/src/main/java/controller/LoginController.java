package controller;

import view.LoginInterfaz;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {

    protected LoginInterfaz loginInt;

    public LoginController(LoginInterfaz loginInt) {
        this.loginInt = loginInt;
    }

    public void startView() {
        loginInt.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
