package com.espe.p2proyecto_lanche_lima;

import view.OptionsRoomsInterfaz;
import controller.OptionsRoomsController;

public class P2Proyecto_Lanche_Lima {

    public static void main(String[] args) {
        OptionsRoomsInterfaz login = new OptionsRoomsInterfaz();
        OptionsRoomsController optRooController = new OptionsRoomsController(login);

        optRooController.startView();
    }
}
