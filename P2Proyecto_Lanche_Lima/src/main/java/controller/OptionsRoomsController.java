package controller;

import java.awt.Color;
import view.OptionsRoomsInterfaz;
import view.LoginInterfaz;
import view.DetailRoomInterfaz;
import model.Rooms;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

    public String formatearFecha(int dia, int mes, int anio) {
        // Verificar si la fecha es válida
        if (esFechaValida(dia, mes, anio)) {
            return String.format("%02d/%02d/%04d", dia, mes, anio);
        } else {
            return "Fecha no válida";
        }
    }

    public void limpiarValidadores() {
        cliInt.lbErrorCheckInDate.setText(" ");
        cliInt.lbErrorCheckOutDate.setText(" ");
    }

    public boolean buscarHabitacion(String habitacionBuscada) {
        try (BufferedReader br = new BufferedReader(new FileReader("RoomsCSVLancheLima.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {  // Leer línea por línea
                if (line.contains(habitacionBuscada)) {  // Verificar si la línea contiene la habitación buscada
                    /*"RoomName|"
                    + "Availability|"
                    + "Capacity|"
                    + "DateRangeReservations|"
                    + "ExtraServices|"
                    + "PricePerNight|"
                    + "RoomSize|"
                    + "RoomType|"
                    + "ServicesIncluded");//La escritura del archivo se realiza usando concetaciones*/
                    rooms.setRoomName("s");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;  // Si no se encuentra, devolver false
    }

    public void roomsInTable() {

    }

    public boolean formatearFecha() {
        boolean dateOK = true;
        // Verificar si la fecha es válida
        if (cliInt.tfCheckInDate_Day.getText().isBlank()) {
            cliInt.lbErrorCheckInDate.setText("Error, ingrese la fecha");
            cliInt.lbErrorCheckInDate.setForeground(Color.red);
            dateOK = false;
        } else if (cliInt.tfCheckInDate_Day.getText().matches("^\\d{2}$")) {
            cliInt.lbErrorCheckInDate.setText("Ingrese solo números");
            cliInt.lbErrorCheckInDate.setForeground(Color.red);
            dateOK = false;
        } else if (cliInt.tfCheckInDate_Month.getText().matches("^\\d{2}$")) {
            cliInt.lbErrorCheckInDate.setText("Ingrese solo números");
            cliInt.lbErrorCheckInDate.setForeground(Color.red);
            dateOK = false;
        } else if (cliInt.tfCheckInDate_Year.getText().matches("^20(2[5-9]|30)$")) {
            cliInt.lbErrorCheckInDate.setText("Ingrese solo números");
            cliInt.lbErrorCheckInDate.setForeground(Color.red);
            dateOK = false;
        } else if (esFechaValida(Integer.parseInt(cliInt.tfCheckInDate_Day.getText()), Integer.parseInt(cliInt.tfCheckInDate_Month.getText()), Integer.parseInt(cliInt.tfCheckInDate_Year.getText()))) {
            cliInt.lbErrorCheckInDate.setText("Fecha inválida");
            cliInt.lbErrorCheckInDate.setForeground(Color.red);
            dateOK = false;
        } else {
            cliInt.lbErrorCheckInDate.setText(" ");
        }

        return dateOK;
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
        } else if (e.getSource() == cliInt.btnSearch) {
            if (formatearFecha()) {
                roomsInTable();
            }
        }

    }
}
