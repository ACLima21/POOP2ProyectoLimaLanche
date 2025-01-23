package controller;

import view.DetailRoomInterfaz;
import view.OptionsRoomsInterfaz;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DetailRoomController implements ActionListener {
    private DetailRoomInterfaz view;
    private OptionsRoomsInterfaz optionsRoomsView;

    public DetailRoomController(DetailRoomInterfaz view, OptionsRoomsInterfaz optionsRoomsView) {
        this.view = view;
        this.optionsRoomsView = optionsRoomsView;
        this.view.btnWantIt.addActionListener(this);
        this.view.btnGetBack.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnGetBack) {
            view.setVisible(false);
            optionsRoomsView.setVisible(true);
        } else if (e.getSource() == view.btnWantIt) {
        }
    }
}