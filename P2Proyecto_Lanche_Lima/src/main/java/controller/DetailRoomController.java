package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import view.OptionsRoomsInterfaz;
import model.Rooms;
import controller.OptionsRoomsController;
import view.DetailRoomInterfaz;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

public class DetailRoomController implements ActionListener, ItemListener {

    private DetailRoomInterfaz detRooInt;
    private OptionsRoomsInterfaz optionsRoomsInterfaz;
    private Rooms rooms;
    private StringBuilder selectedText = new StringBuilder();

    public DetailRoomController(DetailRoomInterfaz detRooInt, Rooms rooms, OptionsRoomsInterfaz optionsRoomsInterfaz) {
        this.detRooInt = detRooInt;
        this.rooms = rooms;
        this.optionsRoomsInterfaz = optionsRoomsInterfaz;
        this.detRooInt.btnGetBack.addActionListener(this);
        this.detRooInt.btnMoreInformation.addActionListener(this);
        this.detRooInt.btnWantIt.addActionListener(this);
        this.detRooInt.cbBreakfast.addItemListener(this);
        this.detRooInt.cbDinner.addItemListener(this);
        this.detRooInt.cbLunch.addItemListener(this);
        this.detRooInt.cbParking.addItemListener(this);
        this.detRooInt.cbTransport.addItemListener(this);
    }

    public void startView() {
        detRooInt.setVisible(true);
        setData();
    }

    public void setData() {
        detRooInt.lbRoomName.setText(rooms.getRoomName());
        detRooInt.lbPricePerNight.setText(String.valueOf(rooms.getPricePerNight()));
        detRooInt.lbCapacity.setText(rooms.getCapacity());
        detRooInt.lbRoomSize.setText(rooms.getRoomSize());
        detRooInt.lbAvailability.setText(rooms.getAvailability() ? "Disponible" : "Ocupado");
        detRooInt.lbServicesIncluded.setText(rooms.getServicesIncluded());
    }

    public void getBack() {
        OptionsRoomsController optionsRoomsController = new OptionsRoomsController(optionsRoomsInterfaz);

        optionsRoomsController.startView();
        detRooInt.dispose();//Borra toda la memoria que se generó con la vista, también borra toda las hijas que se activaron desde allí.
    }

    public void moreInformationOfTheServices() {
        JOptionPane.showMessageDialog(detRooInt,
                """
                Desayuno (10 $ c/u): Disfruta de un delicioso desayuno tipo buffet o a la carta,con opciones que incluyen
                frutas frescas, jugos naturales, pan recién horneado, café, y más.
                Almuerzo (15 $ c/u): Un almuerzo completo que ofrece una selección de entradas, platos principales y
                postres,preparado con ingredientes frescos y opciones para todos los gustos.
                Cena (20 $ c/u): Termina tu día con una cena exquisita, ya sea ligera o completa, acompañada de bebidas y
                un ambiente relajante en nuestro restaurante.
                Estacionamiento (8 $): Servicio de estacionamiento seguro y vigilado, con acceso las 24 horas para mayor
                comodidad durante tu estadía.
                Transporte (20 $): Traslados cómodos y seguros desde y hacia el aeropuerto, estaciones de bus o puntos
                de interés, con vehículos modernos y conductores profesionales.""", "INFORMACIÓN DE LAS HABITACIONES", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == detRooInt.btnGetBack) {
            getBack();
        } else if (e.getSource() == detRooInt.btnMoreInformation) {
            moreInformationOfTheServices();
        } else if (e.getSource() == detRooInt.btnWantIt) {

        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        // Obtenemos el JCheckBox que ha cambiado
        JCheckBox source = (JCheckBox) e.getSource();
        double price = rooms.getPricePerNight();

        // Verificamos si el estado ha cambiado a seleccionado
        if (e.getStateChange() == ItemEvent.SELECTED) {
            // Si está seleccionado, agregar el texto
            if (selectedText.length() > 0) {
                selectedText.append(", ");
            }
            selectedText.append(source.getText());
            switch (source.getText()) {
                case "Desayuno":
                    price = rooms.getPricePerNight() + 10 * Character.getNumericValue(rooms.getCapacity().charAt(0));
                    break;
                case "Almuerzo":
                    price = rooms.getPricePerNight() + 15 * Character.getNumericValue(rooms.getCapacity().charAt(0));
                    break;
                case "Cena":
                    price = rooms.getPricePerNight() + 20 * Character.getNumericValue(rooms.getCapacity().charAt(0));
                    break;
                case "Estacionamiento":
                    price = rooms.getPricePerNight() + 8;
                    break;
                case "Transporte":
                    price = rooms.getPricePerNight() + 20;
                    break;
                default:
                    break;
            }
            rooms.setPricePerNight(price);
        } else if (e.getStateChange() == ItemEvent.DESELECTED) {
            // Si se deselecciona, remover el texto
            String textToRemove = source.getText();
            String[] selectedItems = selectedText.toString().split(", ");
            selectedText.setLength(0);  // Limpiamos el StringBuilder
            for (String item : selectedItems) {
                if (!item.equals(textToRemove)) {
                    if (selectedText.length() > 0) {
                        selectedText.append(", ");
                    }
                    selectedText.append(item);
                }
            }
            switch (source.getText()) {
                case "Desayuno":
                    price = rooms.getPricePerNight() - 10 * Character.getNumericValue(rooms.getCapacity().charAt(0));
                    break;
                case "Almuerzo":
                    price = rooms.getPricePerNight() - 15 * Character.getNumericValue(rooms.getCapacity().charAt(0));
                    break;
                case "Cena":
                    price = rooms.getPricePerNight() - 20 * Character.getNumericValue(rooms.getCapacity().charAt(0));
                    break;
                case "Estacionamiento":
                    price = rooms.getPricePerNight() - 8;
                    break;
                case "Transporte":
                    price = rooms.getPricePerNight() - 20;
                    break;
                default:
                    break;
            }
            rooms.setPricePerNight(price);
        }

        rooms.setExtraServices(selectedText.toString());
        detRooInt.lbPricePerNight.setText(String.valueOf(rooms.getPricePerNight()));
    }

}
