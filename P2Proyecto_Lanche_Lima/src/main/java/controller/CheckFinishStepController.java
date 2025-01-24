package controller;

import com.mongodb.client.MongoCollection;
import java.awt.Color;
import view.CheckFinishStepInterfaz;
import view.OptionsRoomsInterfaz;
import model.Rooms;
import model.Client;
import model.connectionMongoDB;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;//Librería para obtener la fecha actual
import java.time.format.DateTimeFormatter;//Librería para ponerle formato a la fecha
import java.time.temporal.ChronoUnit;//{ref1}
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.bson.Document;

public class CheckFinishStepController implements ActionListener {

    protected Rooms rooms;
    protected Client client;
    protected connectionMongoDB mongo = new connectionMongoDB();
    protected CheckFinishStepInterfaz checkFinishStepInterfaz;
    protected boolean newUser;

    public CheckFinishStepController(Rooms rooms, Client client, boolean newUser, CheckFinishStepInterfaz checkFinishStepInterfaz) {
        this.rooms = rooms;
        this.client = client;
        this.newUser = newUser;
        this.checkFinishStepInterfaz = checkFinishStepInterfaz;
        this.checkFinishStepInterfaz.btnConfirmReservation.addActionListener(this);
        this.checkFinishStepInterfaz.btnSearch.addActionListener(this);
        this.checkFinishStepInterfaz.btnModify.addActionListener(this);
        this.checkFinishStepInterfaz.btnDelete.addActionListener(this);
        this.checkFinishStepInterfaz.btnLogout.addActionListener(this);
        mongo.createConnection();
    }

    public void iniciarView() {
        checkFinishStepInterfaz.setVisible(true);
        if (newUser) {
            loadDataForReservation();
            checkFinishStepInterfaz.btnConfirmReservation.setText("Confirmar");
        } else {
            checkFinishStepInterfaz.btnConfirmReservation.setText("Agregar");
        }
    }

    public void totalToPay() {
        // Dividir el String en dos fechas
        String[] dates = rooms.getDateRangeReservations().split("/");
        String startDateStr = dates[0];
        String endDateStr = dates[1];

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");//se guarda con este formato

        //SE PARSEAN LAS FECHAS A LocalDate
        LocalDate startDate = LocalDate.parse(startDateStr, formatter);
        LocalDate endDate = LocalDate.parse(endDateStr, formatter);

        int nights = (int) ChronoUnit.DAYS.between(startDate, endDate);// Calculo de la diferencia en días y casteado a entero

        rooms.setTotalToPay(rooms.getPricePerNight() * nights);
    }

    public void loadDataForReservation() {
        checkFinishStepInterfaz.btnSearch.setEnabled(false);
        DefaultTableModel tableModel = (DefaultTableModel) checkFinishStepInterfaz.tbReservationResume.getModel();
        tableModel.setRowCount(0);//Limpia todas las filas de la tabla

        checkFinishStepInterfaz.lbTitleForReservation.setText("Resumen de la reserva - " + client.getUsername());
//RoomType,Capacity,PricePerNight, DateRangeReservations, DateRangeReservations, costo total
        Object[] row = {
            rooms.getRoomType(),
            rooms.getCapacity(),
            rooms.getPricePerNight(),
            rooms.getDateRangeReservations(),
            rooms.getExtraServices(),
            rooms.getTotalToPay()
        };
        tableModel.addRow(row);

        checkFinishStepInterfaz.tbReservationResume.setModel(tableModel);

        System.out.println("Se cargó la tabla");
    }

    public void addClientWithReservationsToMongo() {
        ArrayList<String> roomToAdd = new ArrayList<>();//variable para guardar las habitaciones
        roomToAdd = client.getRoomsNames();//Primero se agregan todas las que ya tenía el cliente reservadas
        roomToAdd.add(rooms.getRoomName());//Ahora se agrega la reserva actual del cliente
        client.setRoomsNames(roomToAdd);//se actualiza el cliente con todas sus reservaciones

        Document doc = new Document("Username", client.getUsername())
                .append("Password", client.getPassword())
                .append("Email", client.getEmail())
                .append("Phone", client.getPhone())
                .append("FullName", client.getFullName())
                .append("Address", client.getAddress())
                .append("Reservations", client.getRoomsNames());
        mongo.insertUsuario(doc);
    }

    public void addClientWithReservationsToTable() {
        DefaultTableModel modelo = (DefaultTableModel) checkFinishStepInterfaz.tbReservationResume.getModel();
        OptionsRoomsController optionsRoomsController = new OptionsRoomsController(null, false, null, null, null);//instancia solo para usar el método createDocOfRooms
        modelo.setRowCount(0);
        for (String room : client.getRoomsNames()) {
            for (Document doc : optionsRoomsController.createDocOfRooms(room)) {
                if (Boolean.parseBoolean(doc.get("Availability").toString())) {//Si la habitación está disponible entonces se obtiene sus datos y se agregan a la tabla
                    Object[] row = {
                        doc.get("RoomName"),
                        doc.get("RoomType"),
                        doc.get("PricePerNight"),
                        doc.get("DateRangeReservations"),
                        doc.get("ExtraServices"),
                        doc.get("TotalToPay")};
                    modelo.addRow(row);
                }
            }
        }
        checkFinishStepInterfaz.tbReservationResume.setModel(modelo);
        System.out.println("\n\nSe cargó la tabla\n\n");
    }

    public void addRoomToClient() {
        if ("Confirmar".equals(checkFinishStepInterfaz.btnConfirmReservation.getText())) {
            //INICIOS
            checkFinishStepInterfaz.btnSearch.setEnabled(true);
            client.reservationDone();

            addClientWithReservationsToMongo();

            addClientWithReservationsToTable();

        } else if ("Agregar".equals(checkFinishStepInterfaz.btnConfirmReservation.getText())) {
            OptionsRoomsInterfaz optionsRoomsInterfaz = new OptionsRoomsInterfaz();
            OptionsRoomsController optionsRoomsController = new OptionsRoomsController(optionsRoomsInterfaz, false, checkFinishStepInterfaz, rooms, client);
            optionsRoomsController.startView();

            checkFinishStepInterfaz.dispose();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == checkFinishStepInterfaz.btnConfirmReservation) {
            if (newUser) {
                addRoomToClient();
            }
        } else if (e.getSource() == checkFinishStepInterfaz.btnSearch) {
        } else if (e.getSource() == checkFinishStepInterfaz.btnModify) {
        } else if (e.getSource() == checkFinishStepInterfaz.btnDelete) {
        } else if (e.getSource() == checkFinishStepInterfaz.btnLogout) {
        }
    }

}

/*
ref1: Esta librería se utiliza para realizar cálculos relacionados con fechas y tiempos, como añadir o restar unidades temporales a un momento dado, o medir la cantidad de unidades entre dos momentos.
 */
