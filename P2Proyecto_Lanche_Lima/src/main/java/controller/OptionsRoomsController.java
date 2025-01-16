package controller;

import view.OptionsRoomsInterfaz;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsRoomsController implements ActionListener {

    protected OptionsRoomsInterfaz cliInt;

    public OptionsRoomsController(OptionsRoomsInterfaz cliInt) {
        this.cliInt = cliInt;
    }

    public void startView() {
        cliInt.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
