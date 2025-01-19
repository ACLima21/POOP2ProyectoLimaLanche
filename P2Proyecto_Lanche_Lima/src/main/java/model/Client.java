package model;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Client extends Person implements InterfaceClient {

    private String username, password;
    private ArrayList<String> roomsNames;

    // constructor
    public Client(String username, String password, ArrayList<String> roomsNames, String fullName, String email, String address, String phone) {
        super(fullName, email, address, phone);
        this.username = username;
        this.password = password;
        this.roomsNames = roomsNames;
    }

    //getter y setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getRoomsNames() {
        return roomsNames;
    }

    public void setRoomsNames(ArrayList<String> roomsNames) {
        this.roomsNames = roomsNames;
    }

    @Override
    public void login() {
        JOptionPane.showMessageDialog(null, "Login exitoso, Bienvenido " + getUsername() + "\nAhora podrá confirmar continuar con su reservación", "INICIO EXITOSO", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void reservationDone() {
        JOptionPane.showMessageDialog(null, "Gracias por su reserva " + getUsername() + "\nQue disfrute su estancia", "RESERVA EXITOSA", JOptionPane.INFORMATION_MESSAGE);
    }

}
