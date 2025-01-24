package controller;

import view.OptionsRoomsInterfaz;
import view.LoginInterfaz;
import view.DetailRoomInterfaz;
import view.CheckFinishStepInterfaz;
import model.Rooms;
import model.Client;
import model.CreationOfRooms;
import java.time.LocalDate;
import java.awt.Color;
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

    protected OptionsRoomsInterfaz viewOptionsRoomsInterfaz;
    protected Rooms rooms;
    protected Client client;
    protected LocalDate date = LocalDate.now();
    protected boolean newUser;
    protected CheckFinishStepInterfaz cfsi = new CheckFinishStepInterfaz();

    public OptionsRoomsController(OptionsRoomsInterfaz viewOptionsRoomsInterfaz, boolean newUser, CheckFinishStepInterfaz cfsi, Rooms rooms, Client client) {
        this.newUser = newUser;
        this.viewOptionsRoomsInterfaz = viewOptionsRoomsInterfaz;
        this.cfsi = cfsi;
        this.rooms = rooms;
        this.client = client;
        this.viewOptionsRoomsInterfaz.btnLogin.addActionListener(this);
        this.viewOptionsRoomsInterfaz.btnRoomDetails.addActionListener(this);
        this.viewOptionsRoomsInterfaz.btnSearch.addActionListener(this);
    }

    public void startView() {
        viewOptionsRoomsInterfaz.setVisible(true);
        limpiarValidadores();

        if (newUser) {
            viewOptionsRoomsInterfaz.btnLogin.setText("Iniciar sesión");
        } else {
            viewOptionsRoomsInterfaz.btnLogin.setText("Ver Reservaciones");
        }
    }

    public boolean esFechaValida(int dia, int mes, int anio) {
        // Comprobar si el mes está en el rango válido
        if (anio == date.getYear()) {
            if (mes < date.getMonthValue() || mes > 12) {
                return false;
            }

            // Comprobar si el día está dentro del rango del mes
            int diasMaximos = obtenerDiasMaximosDelMes(mes, anio);
            return dia >= date.getDayOfMonth() && dia <= diasMaximos;
        }
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
        viewOptionsRoomsInterfaz.lbErrorCheckInDate.setText(" ");
        viewOptionsRoomsInterfaz.lbErrorCheckOutDate.setText(" ");
    }

    public List<Document> createDocOfRooms(String varToCompare) {
        List<Document> documents = new ArrayList<>();
        Document searchedRoomsDoc = null;

        try (BufferedReader br = new BufferedReader(new FileReader("RoomsCSVLancheLima.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                //System.out.println("\n\n" + line + "\t" + varToCompare + "\n\n");
                if (line.contains(varToCompare)) {
                    String[] partes = line.split("\\|"); // Dividir el string por "|"
                    searchedRoomsDoc = new Document("RoomName", line.split("\\|")[0])
                            .append("Availability", line.split("\\|")[1])
                            .append("Capacity", line.split("\\|")[2])
                            .append("DateRangeReservations", line.split("\\|")[3])
                            .append("ExtraServices", line.split("\\|")[4])
                            .append("PricePerNight", line.split("\\|")[5])
                            .append("RoomSize", line.split("\\|")[6])
                            .append("RoomType", line.split("\\|")[7])
                            .append("ServicesIncluded", line.split("\\|")[8])
                            .append("TotalToPay", line.split("\\|")[9]);
                    documents.add(searchedRoomsDoc);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return documents;
    }

    public void roomsInTable() {
        DefaultTableModel modeloTabla = (DefaultTableModel) viewOptionsRoomsInterfaz.tbRoomInformation.getModel();
        modeloTabla.setRowCount(0);//Limpia todas las filas de la tabla
        // Iterar sobre los documentos y añadir filas al modelo de la tabla
        System.out.println("TEST" + viewOptionsRoomsInterfaz.cbGuests.getSelectedItem().toString());
        for (Document doc : createDocOfRooms(viewOptionsRoomsInterfaz.cbGuests.getSelectedItem().toString())) {
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
            JOptionPane.showMessageDialog(viewOptionsRoomsInterfaz, "Lo sentimos, no hay habitaciones disponibles", "HABITACIONES AGOTADAS", JOptionPane.INFORMATION_MESSAGE);
        } else {//si hay una o más de una
            viewOptionsRoomsInterfaz.tbRoomInformation.setModel(modeloTabla);
            System.out.println("\n\nSe cargó la tabla\n\n");
        }

    }

    public int getStartAnio() {
        return Character.getNumericValue(String.valueOf(date.getYear()).charAt(3));
    }

    public boolean formatearFecha() {
        boolean dateOK = true;

        // Verificar si la fecha es válida
        if (viewOptionsRoomsInterfaz.tfCheckInDate_Day.getText().isBlank() || viewOptionsRoomsInterfaz.tfCheckInDate_Month.getText().isBlank() || viewOptionsRoomsInterfaz.tfCheckInDate_Year.getText().isBlank()) {
            viewOptionsRoomsInterfaz.lbErrorCheckInDate.setText("Error, ingrese la fecha");
            viewOptionsRoomsInterfaz.lbErrorCheckInDate.setForeground(Color.red);
            dateOK = false;
        } else if (!viewOptionsRoomsInterfaz.tfCheckInDate_Day.getText().matches("^\\d{2}$")) {
            System.out.println("el día está mal");
            viewOptionsRoomsInterfaz.lbErrorCheckInDate.setText("Ingrese solo números");
            viewOptionsRoomsInterfaz.lbErrorCheckInDate.setForeground(Color.red);
            dateOK = false;
        } else if (!viewOptionsRoomsInterfaz.tfCheckInDate_Month.getText().matches("^\\d{2}$")) {
            System.out.println("el mes está mal");
            viewOptionsRoomsInterfaz.lbErrorCheckInDate.setText("Ingrese solo números");
            viewOptionsRoomsInterfaz.lbErrorCheckInDate.setForeground(Color.red);
            dateOK = false;
        } else if (!viewOptionsRoomsInterfaz.tfCheckInDate_Year.getText().matches("^20(2[" + getStartAnio() + "-9]|30)$")) {
            System.out.println("el año está mal");
            viewOptionsRoomsInterfaz.lbErrorCheckInDate.setText("Ingrese solo números");
            viewOptionsRoomsInterfaz.lbErrorCheckInDate.setForeground(Color.red);
            dateOK = false;
        } else if (!esFechaValida(Integer.parseInt(viewOptionsRoomsInterfaz.tfCheckInDate_Day.getText()), Integer.parseInt(viewOptionsRoomsInterfaz.tfCheckInDate_Month.getText()), Integer.parseInt(viewOptionsRoomsInterfaz.tfCheckInDate_Year.getText()))) {
            viewOptionsRoomsInterfaz.lbErrorCheckInDate.setText("Fecha inválida");
            viewOptionsRoomsInterfaz.lbErrorCheckInDate.setForeground(Color.red);
            dateOK = false;
        } else {
            viewOptionsRoomsInterfaz.lbErrorCheckInDate.setText(" ");
        }

        //FECHA DE SALIDA
        if (viewOptionsRoomsInterfaz.tfCheckOutDate_Day.getText().isBlank() || viewOptionsRoomsInterfaz.tfCheckOutDate_Month.getText().isBlank() || viewOptionsRoomsInterfaz.tfCheckOutDate_Year.getText().isBlank()) {
            viewOptionsRoomsInterfaz.lbErrorCheckOutDate.setText("Error, ingrese la fecha");
            viewOptionsRoomsInterfaz.lbErrorCheckOutDate.setForeground(Color.red);
            dateOK = false;
        } else if (!viewOptionsRoomsInterfaz.tfCheckOutDate_Day.getText().matches("^\\d{2}$")
                || !viewOptionsRoomsInterfaz.tfCheckOutDate_Month.getText().matches("^\\d{2}$")
                || !viewOptionsRoomsInterfaz.tfCheckOutDate_Year.getText().matches("^20(2[5-9]|30)$")) {
            System.out.println("el día, mes, o año está mal");
            viewOptionsRoomsInterfaz.lbErrorCheckOutDate.setText("Formato: dd/mm/aaaa");
            viewOptionsRoomsInterfaz.lbErrorCheckOutDate.setForeground(Color.red);
            dateOK = false;
        } else if (!esFechaValida(Integer.parseInt(viewOptionsRoomsInterfaz.tfCheckOutDate_Day.getText()), Integer.parseInt(viewOptionsRoomsInterfaz.tfCheckOutDate_Month.getText()), Integer.parseInt(viewOptionsRoomsInterfaz.tfCheckOutDate_Year.getText()))) {
            viewOptionsRoomsInterfaz.lbErrorCheckOutDate.setText("Fecha inválida");
            viewOptionsRoomsInterfaz.lbErrorCheckOutDate.setForeground(Color.red);
            dateOK = false;
        } else if (viewOptionsRoomsInterfaz.tfCheckOutDate_Year.getText().compareTo(viewOptionsRoomsInterfaz.tfCheckInDate_Year.getText()) < 0
                || (viewOptionsRoomsInterfaz.tfCheckOutDate_Year.getText().compareTo(viewOptionsRoomsInterfaz.tfCheckInDate_Year.getText()) == 0
                && viewOptionsRoomsInterfaz.tfCheckOutDate_Month.getText().compareTo(viewOptionsRoomsInterfaz.tfCheckInDate_Month.getText()) < 0)
                || (viewOptionsRoomsInterfaz.tfCheckOutDate_Year.getText().compareTo(viewOptionsRoomsInterfaz.tfCheckInDate_Year.getText()) == 0
                && viewOptionsRoomsInterfaz.tfCheckOutDate_Month.getText().compareTo(viewOptionsRoomsInterfaz.tfCheckInDate_Month.getText()) == 0
                && viewOptionsRoomsInterfaz.tfCheckOutDate_Day.getText().compareTo(viewOptionsRoomsInterfaz.tfCheckInDate_Day.getText()) < 0)) {//{ref1}
            viewOptionsRoomsInterfaz.lbErrorCheckOutDate.setText("Error: La fecha es menor");
            viewOptionsRoomsInterfaz.lbErrorCheckOutDate.setForeground(Color.red);
            dateOK = false;
        } else {
            viewOptionsRoomsInterfaz.lbErrorCheckOutDate.setText(" ");
        }
        return dateOK;
    }

    public void sendToDetails() {
        if (viewOptionsRoomsInterfaz.tbRoomInformation.getSelectedRow() >= 0) {//Para el caso en el que si se seleccionó una habitación
            System.out.println("\n\n" + viewOptionsRoomsInterfaz.tbRoomInformation.getValueAt(viewOptionsRoomsInterfaz.tbRoomInformation.getSelectedRow(), 0).toString() + "\t" + viewOptionsRoomsInterfaz.tbRoomInformation.getSelectedRow() + "\n\n");
            rooms = new Rooms("", true, "", "", "", 0, "", "", "", 0);
            rooms.setRoomName(viewOptionsRoomsInterfaz.tbRoomInformation.getValueAt(viewOptionsRoomsInterfaz.tbRoomInformation.getSelectedRow(), 0).toString());

            Document doc = createDocOfRooms(rooms.getRoomName()).get(0);//Ahora doc contiene los elementos de la habitación que se escogió.
            rooms.setAvailability(Boolean.parseBoolean(doc.get("Availability").toString()));
            rooms.setCapacity(doc.get("Capacity").toString());
            rooms.setDateRangeReservations(viewOptionsRoomsInterfaz.tfCheckInDate_Day.getText()
                    + "-" + viewOptionsRoomsInterfaz.tfCheckInDate_Month.getText()
                    + "-" + viewOptionsRoomsInterfaz.tfCheckInDate_Year.getText()
                    + "/" + viewOptionsRoomsInterfaz.tfCheckOutDate_Day.getText()
                    + "-" + viewOptionsRoomsInterfaz.tfCheckOutDate_Month.getText()
                    + "-" + viewOptionsRoomsInterfaz.tfCheckOutDate_Year.getText());
            rooms.setExtraServices(doc.get("ExtraServices").toString());
            rooms.setPricePerNight(Double.parseDouble(doc.get("PricePerNight").toString()));
            rooms.setRoomSize(doc.get("RoomSize").toString());
            rooms.setRoomType(doc.get("RoomType").toString());
            rooms.setServicesIncluded(doc.get("ServicesIncluded").toString());

            DetailRoomInterfaz intDetail = new DetailRoomInterfaz();
            DetailRoomController detailOfRoom = new DetailRoomController(intDetail, rooms, viewOptionsRoomsInterfaz);
            detailOfRoom.startView();

            viewOptionsRoomsInterfaz.dispose();//Borra toda la memoria que se generó con la vista, también borra toda las hijas que se activaron desde allí.
        } else {//Para el caso en el que no se seleccione ninguna habitación
            JOptionPane.showMessageDialog(viewOptionsRoomsInterfaz, "Debe seleccionar una habitación para ver sus detalles", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewOptionsRoomsInterfaz.btnLogin && viewOptionsRoomsInterfaz.btnLogin.getText().equals("Iniciar sesión")) {
            LoginInterfaz li = new LoginInterfaz();
            Client clientForNow = new Client("", "", null, "", "", "", "");
            LoginController loginController = new LoginController(li, null, clientForNow, rooms);

            loginController.iniciarView();
            viewOptionsRoomsInterfaz.dispose();
        } else if (e.getSource() == viewOptionsRoomsInterfaz.btnLogin && viewOptionsRoomsInterfaz.btnLogin.getText().equals("Ver Reservaciones")) {
            CheckFinishStepController cfsc = new CheckFinishStepController(rooms, client, false, cfsi);
            cfsc.iniciarView();
        } else if (e.getSource() == viewOptionsRoomsInterfaz.btnRoomDetails) {
            sendToDetails();
        } else if (e.getSource() == viewOptionsRoomsInterfaz.btnSearch) {
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
