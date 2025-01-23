package controller;

import java.awt.Color;
import view.OptionsRoomsInterfaz;
import view.LoginInterfaz;
import view.DetailRoomInterfaz;
import model.Rooms;
import model.CreationOfRooms;
import controller.DetailRoomController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.bson.Document;

public class OptionsRoomsController implements ActionListener {

    protected OptionsRoomsInterfaz cliInt;
    protected Rooms rooms;

    public OptionsRoomsController(OptionsRoomsInterfaz cliInt) {
        this.cliInt = cliInt;
        this.cliInt.btnLogin.addActionListener(this);
        this.cliInt.btnRoomDetails.addActionListener(this);
        this.cliInt.btnSearch.addActionListener(this);
    }

    public void startView() {
        cliInt.setVisible(true);
        limpiarValidadores();
    }

    public boolean esFechaValida(int dia, int mes, int anio) {
        // Comprobar si el mes está en el rango válido
        if (mes < 1 || mes > 12) {
            return false;
        }

        // Comprobar si el día está dentro del rango del mes
        int diasMaximos = obtenerDiasMaximosDelMes(mes, anio);

        return dia >= 1 && dia <= diasMaximos;
    }

    private int obtenerDiasMaximosDelMes(int mes, int anio) {
        // Días de los meses (sin considerar años bisiestos)
        switch (mes) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31; // Meses con 31 días
            case 4:
            case 6:
            case 9:
            case 11:
                return 30; // Meses con 30 días
            case 2:
                return esAnioBisiesto(anio) ? 29 : 28; // Febrero con 29 en año bisiesto, 28 en otro
            default:
                return 0; // Este caso no debería ocurrir debido a la validación previa del mes
        }
    }

    private boolean esAnioBisiesto(int anio) {
        // Un año es bisiesto si es divisible por 4, pero no por 100, a menos que sea divisible por 400
        return (anio % 4 == 0 && (anio % 100 != 0 || anio % 400 == 0));
    }

    public void limpiarValidadores() {
        cliInt.lbErrorCheckInDate.setText(" ");
        cliInt.lbErrorCheckOutDate.setText(" ");
    }

    public List<Document> createDocOfRooms(String varToCompare) {
        List<Document> documents = new ArrayList<>();
        Document searchedRoomsDoc = null;

        try (BufferedReader br = new BufferedReader(new FileReader("RoomsCSVLancheLima.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(varToCompare)) {
                    System.out.println("\n\n" + line + "\t" + varToCompare + "\n\n");
                    String[] partes = line.split("\\|"); // Dividir el string por "|"
                    searchedRoomsDoc = new Document("RoomName", line.split("\\|")[0])
                            .append("Availability", line.split("\\|")[1])
                            .append("Capacity", line.split("\\|")[2])
                            .append("DateRangeReservations", line.split("\\|")[3])
                            .append("ExtraServices", line.split("\\|")[4])
                            .append("PricePerNight", line.split("\\|")[5])
                            .append("RoomSize", line.split("\\|")[6])
                            .append("RoomType", line.split("\\|")[7])
                            .append("ServicesIncluded", line.split("\\|")[8]);
                    documents.add(searchedRoomsDoc);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return documents;
    }

    public void roomsInTable() {
        DefaultTableModel modeloTabla = (DefaultTableModel) cliInt.tbRoomInformation.getModel();
        modeloTabla.setRowCount(0);//Limpia todas las filas de la tabla
        // Iterar sobre los documentos y añadir filas al modelo de la tabla
        for (Document doc : createDocOfRooms(cliInt.cbGuests.getSelectedItem().toString())) {
            if (Boolean.parseBoolean(doc.get("Availability").toString())) {//Si la habitación está disponible entonces se obtiene sus datos y se agregan a la tabla
                Object[] row = {
                    doc.get("RoomName"),
                    doc.get("RoomType"),
                    doc.get("PricePerNight")
                };
                modeloTabla.addRow(row);
            }
        }
        if (modeloTabla.getRowCount() == 0) {//Si no hay filas en la tabla
            JOptionPane.showMessageDialog(cliInt, "Lo sentimos, no hay habitaciones disponibles", "HABITACIONES AGOTADAS", JOptionPane.INFORMATION_MESSAGE);
        } else {//si hay una o más de una
            cliInt.tbRoomInformation.setModel(modeloTabla);
            System.out.println("\n\nSe cargó la tabla\n\n");
        }
    }

    public boolean formatearFecha() {
        boolean dateOK = true;

        // Verificar si la fecha es válida
        if (cliInt.tfCheckInDate_Day.getText().isBlank() || cliInt.tfCheckInDate_Month.getText().isBlank() || cliInt.tfCheckInDate_Year.getText().isBlank()) {
            cliInt.lbErrorCheckInDate.setText("Error, ingrese la fecha");
            cliInt.lbErrorCheckInDate.setForeground(Color.red);
            dateOK = false;
        } else if (!cliInt.tfCheckInDate_Day.getText().matches("^\\d{2}$")) {
            System.out.println("el día está mal");
            cliInt.lbErrorCheckInDate.setText("Ingrese solo números");
            cliInt.lbErrorCheckInDate.setForeground(Color.red);
            dateOK = false;
        } else if (!cliInt.tfCheckInDate_Month.getText().matches("^\\d{2}$")) {
            System.out.println("el mes está mal");
            cliInt.lbErrorCheckInDate.setText("Ingrese solo números");
            cliInt.lbErrorCheckInDate.setForeground(Color.red);
            dateOK = false;
        } else if (!cliInt.tfCheckInDate_Year.getText().matches("^20(2[5-9]|30)$")) {
            System.out.println("el año está mal");
            cliInt.lbErrorCheckInDate.setText("Ingrese solo números");
            cliInt.lbErrorCheckInDate.setForeground(Color.red);
            dateOK = false;
        } else if (!esFechaValida(Integer.parseInt(cliInt.tfCheckInDate_Day.getText()), Integer.parseInt(cliInt.tfCheckInDate_Month.getText()), Integer.parseInt(cliInt.tfCheckInDate_Year.getText()))) {
            cliInt.lbErrorCheckInDate.setText("Fecha inválida");
            cliInt.lbErrorCheckInDate.setForeground(Color.red);
            dateOK = false;
        } else {
            cliInt.lbErrorCheckInDate.setText(" ");
        }

        //FECHA DE SALIDA
        if (cliInt.tfCheckOutDate_Day.getText().isBlank() || cliInt.tfCheckOutDate_Month.getText().isBlank() || cliInt.tfCheckOutDate_Year.getText().isBlank()) {
            cliInt.lbErrorCheckOutDate.setText("Error, ingrese la fecha");
            cliInt.lbErrorCheckOutDate.setForeground(Color.red);
            dateOK = false;
        } else if (!cliInt.tfCheckOutDate_Day.getText().matches("^\\d{2}$")
                || !cliInt.tfCheckOutDate_Month.getText().matches("^\\d{2}$")
                || !cliInt.tfCheckOutDate_Year.getText().matches("^20(2[5-9]|30)$")) {
            System.out.println("el día, mes, o año está mal");
            cliInt.lbErrorCheckOutDate.setText("Formato: dd/mm/aaaa");
            cliInt.lbErrorCheckOutDate.setForeground(Color.red);
            dateOK = false;
        } else if (!esFechaValida(Integer.parseInt(cliInt.tfCheckOutDate_Day.getText()), Integer.parseInt(cliInt.tfCheckOutDate_Month.getText()), Integer.parseInt(cliInt.tfCheckOutDate_Year.getText()))) {
            cliInt.lbErrorCheckOutDate.setText("Fecha inválida");
            cliInt.lbErrorCheckOutDate.setForeground(Color.red);
            dateOK = false;
        } else if (cliInt.tfCheckOutDate_Year.getText().compareTo(cliInt.tfCheckInDate_Year.getText()) < 0
                || (cliInt.tfCheckOutDate_Year.getText().compareTo(cliInt.tfCheckInDate_Year.getText()) == 0
                && cliInt.tfCheckOutDate_Month.getText().compareTo(cliInt.tfCheckInDate_Month.getText()) < 0)
                || (cliInt.tfCheckOutDate_Year.getText().compareTo(cliInt.tfCheckInDate_Year.getText()) == 0
                && cliInt.tfCheckOutDate_Month.getText().compareTo(cliInt.tfCheckInDate_Month.getText()) == 0
                && cliInt.tfCheckOutDate_Day.getText().compareTo(cliInt.tfCheckInDate_Day.getText()) < 0)) {//{ref1}
            cliInt.lbErrorCheckOutDate.setText("Error: La fecha es menor");
            cliInt.lbErrorCheckOutDate.setForeground(Color.red);
            dateOK = false;
        } else {
            cliInt.lbErrorCheckOutDate.setText(" ");
        }
        return dateOK;
    }

    public void sendToDetails() {
        if (cliInt.tbRoomInformation.getSelectedRow() >= 0) {//Para el caso en el que si se seleccionó una habitación
            rooms.setRoomName(cliInt.tbRoomInformation.getValueAt(cliInt.tbRoomInformation.getSelectedRow(), 0).toString());

            Document doc = createDocOfRooms(rooms.getRoomName()).get(0);//Ahora doc contiene los elementos de la habitación que se escogió.
            rooms.setAvailability(Boolean.parseBoolean(doc.get("Availability").toString()));
            rooms.setCapacity(doc.get("Capacity").toString());
            rooms.setDateRangeReservations(doc.get("DateRangeReservations").toString());
            rooms.setExtraServices(doc.get("ExtraServices").toString());
            rooms.setPricePerNight(Double.parseDouble(doc.get("PricePerNight").toString()));
            rooms.setRoomSize(doc.get("RoomSize").toString());
            rooms.setRoomType(doc.get("RoomType").toString());
            rooms.setServicesIncluded(doc.get("ServicesIncluded").toString());

            DetailRoomInterfaz intDetail = new DetailRoomInterfaz();
            DetailRoomController detailOfRoom = new DetailRoomController(intDetail, rooms);
            detailOfRoom.startView();
        } else {//Para el caso en el que no se seleccione ninguna habitación
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cliInt.btnLogin) {
            LoginInterfaz loginInt = new LoginInterfaz();
            loginInt.setVisible(true);
            cliInt.dispose();
        } else if (e.getSource() == cliInt.btnRoomDetails) {
            sendToDetails();
            cliInt.dispose();
        } else if (e.getSource() == cliInt.btnSearch) {
            if (formatearFecha()) {
                CreationOfRooms validateRooms = new CreationOfRooms();
                validateRooms.confirmTheFileExists();//Se llama al método para verificar que el documento de las habitaciones exista.
                roomsInTable();
            }
        }
    }
}

/*
ref1: se utiliza el compareTo para comparar los valores Unicode de dos string, de esta manera se puede saber que número es menor o mayor sin tener que transformalo de string a formato número, y el <0 es para saber si el valor es menor.
 */
