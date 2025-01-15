package com.espe.p2proyecto_lanche_lima;

import view.LoginInterfaz;
import controller.LoginController;

public class P2Proyecto_Lanche_Lima {

    public static void main(String[] args) {
        LoginInterfaz login = new LoginInterfaz();
        LoginController loginCont = new LoginController(login);

        loginCont.startView();
    }
}
