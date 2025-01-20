package com.espe.p2proyecto_lanche_lima;

import view.OptionsRoomsInterfaz;
import controller.OptionsRoomsController;
import model.CreationOfRooms;
import view.LoginInterfaz;
import controller.LoginController;
public class P2Proyecto_Lanche_Lima {

    public static void main(String[] args) {
        /*OptionsRoomsInterfaz login = new OptionsRoomsInterfaz();
        OptionsRoomsController optRooController = new OptionsRoomsController(login);
        CreationOfRooms creatRooms = new CreationOfRooms();

        creatRooms.confirmTheFileExists();
        optRooController.startView();
       LoginInterfaz loginInterfaz = new LoginInterfaz();
        LoginController loginController = new LoginController(loginInterfaz);
        loginController.startView(); */
        OptionsRoomsInterfaz optionsRoomsInterfaz = new OptionsRoomsInterfaz();
        OptionsRoomsController optionsRoomsController = new OptionsRoomsController(optionsRoomsInterfaz);
        optionsRoomsController.startView(); 
        
    }
}
