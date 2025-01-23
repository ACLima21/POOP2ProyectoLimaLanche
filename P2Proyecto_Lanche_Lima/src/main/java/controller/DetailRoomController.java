package controller;

import java.awt.event.ActionEvent;
import model.Rooms;
import view.DetailRoomInterfaz;
import java.awt.event.ActionListener;

public class DetailRoomController implements ActionListener {

    private DetailRoomInterfaz detRooInt;
    private Rooms rooms;

    public DetailRoomController(DetailRoomInterfaz detRooInt, Rooms rooms) {
        this.detRooInt = detRooInt;
        this.rooms = rooms;
    }

    public void startView() {
        detRooInt.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
