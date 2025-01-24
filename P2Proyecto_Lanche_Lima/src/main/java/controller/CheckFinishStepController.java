package controller;

import com.mongodb.client.MongoCollection;
import java.awt.Color;
import view.CheckFinishStepInterfaz;
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
        OptionsRoomsController optionsRoomsController = new OptionsRoomsController(null);
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
            checkFinishStepInterfaz.btnConfirmReservation.setText("Agregar");
            checkFinishStepInterfaz.btnSearch.setEnabled(true);
            client.reservationDone();

            addClientWithReservationsToMongo();

            addClientWithReservationsToTable();

        } else if ("Agregar".equals(checkFinishStepInterfaz.btnConfirmReservation.getText())) {

        }

    }

    /*public void searchReservations() {
        MongoCollection<Document> mongoCollection = mongo.getMongoDB().getCollection("Estudiantes");

        if (mongoCollection.countDocuments() != 0) {//si existen documentos en la collección
            if (checkFinishStepInterfaz.btnSearch.getText().equals("Buscar")) {//Si el botón tiene ese texto
                String roomToSearch = JOptionPane.showInputDialog(checkFinishStepInterfaz, "Ingrese la habitación a buscar:", "BÚSQUEDA DE LA HABITACIÓN", JOptionPane.QUESTION_MESSAGE);//El JOption recibe el string de la habitación a Buscar
                Document credentialsDocToSearch = new Document("RoomName", roomToSearch);
                Document documentWithAllInformation = (Document) mongo.searchSelector(credentialsDocToSearch).get(0);
                if (!documentWithAllInformation.isEmpty()) {//Si el documento no está vacío
                    DefaultTableModel modelo = (DefaultTableModel) checkFinishStepInterfaz.tbReservationResume.getModel();
                    modelo.setRowCount(0);
                    modelo.addRow(new Object[]{
                        documentWithAllInformation.getString("RoomType"),
                        documentWithAllInformation.getString("Capacity"),
                        documentWithAllInformation.getInteger("PricePerNight")
                    });
                    checkFinishStepInterfaz.btnSearch.setText("Refrescar");
                    checkFinishStepInterfaz.btnSearch.setForeground(new Color(200, 144, 167));
                    checkFinishStepInterfaz.btnSearch.setBackground(new Color(33, 33, 33));
                    checkFinishStepInterfaz.tbReservationResume.setModel(modelo);
                } else {//Si el documento está vacío
                    JOptionPane.showMessageDialog(checkFinishStepInterfaz, "El estudiante no se encontró en la base de datos", "Búsqueda", JOptionPane.WARNING_MESSAGE);
                    loadDataTable();
                }

            } else if (checkFinishStepInterfaz.btnSearch.getText().equals("Refrescar")) {//Si el botón tiene este otro texto
                checkFinishStepInterfaz.btnSearch.setText("Buscar");
                checkFinishStepInterfaz.btnSearch.setForeground(new Color(33, 33, 33));
                checkFinishStepInterfaz.btnSearch.setBackground(new Color(200, 144, 167));
                loadDataTable();
            } else {
                System.out.println("\n\nError, el botón no tiene el texto esperado\n\n");
            }
        } else {//Si no existen documentos en la collección
            JOptionPane.showMessageDialog(checkFinishStepInterfaz, "La base de datos está vacía, no se puede realizar esta opción.");
        }
    }*/
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
