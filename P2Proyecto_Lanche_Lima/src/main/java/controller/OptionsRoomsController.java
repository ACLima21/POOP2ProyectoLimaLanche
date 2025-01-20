package controller;

import view.OptionsRoomsInterfaz;
import view.LoginInterfaz;
import view.DetailRoomInterfaz;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsRoomsController implements ActionListener {

    protected OptionsRoomsInterfaz cliInt;

    public OptionsRoomsController(OptionsRoomsInterfaz cliInt) {
        this.cliInt = cliInt;
        this.cliInt = cliInt;
        this.cliInt.btnLogin.addActionListener(this);
        this.cliInt.btnRoomDetails.addActionListener(this); 
    }

    public void startView() {
        cliInt.setVisible(true);
    }

    @Override
     public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cliInt.btnLogin) {
            LoginInterfaz loginInt = new LoginInterfaz();
            loginInt.setVisible(true);
            cliInt.dispose(); 
        } else if (e.getSource() == cliInt.btnRoomDetails) {
            DetailRoomInterfaz detailRoomInt = new DetailRoomInterfaz();
            detailRoomInt.setVisible(true);
            cliInt.dispose(); 
        }
    
    }
}
