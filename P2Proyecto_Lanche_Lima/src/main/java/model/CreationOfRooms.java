package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class CreationOfRooms {

    private String fileName = "RoomsCSVLancheLima.csv";//nombre del archivo
    private HashMap<String, Rooms> roomsMap;

    public CreationOfRooms() {
        roomsMap = new HashMap<>();
        loadRoomsData();
    }

    public void createFileWithDefaultRooms() {//Método para guardar la información en un archivo CSV
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {//Se intenta escribir el objeto en un archivo{ref1}
            bw.write("RoomName|"
                    + "Availability|"
                    + "Capacity|"
                    + "DateRangeReservations|"
                    + "ExtraServices|"
                    + "PricePerNight|"
                    + "RoomSize|"
                    + "RoomType|"
                    + "ServicesIncluded");//La escritura del archivo se realiza usando concetaciones
            bw.newLine();
            for (int i = 1; i <= 20; i++) {
                System.out.println(String.format("Room%02d", i) + "\t también: " + i);
                saveCSV(bw, roomsMap.get(String.format("Room%02d", i)));
            }
        } catch (IOException e) {//Error del tipo, entrada y salida de datos
            System.out.println("Error al guardar el archivo CSV" + e.getMessage());//Si ocurre error se imprime el texto y la descripción del error ocurrido
        }
    }

    public void confirmTheFileExists() {
        // Verificar si el archivo existe y no está vacío
        File file = new File(fileName);
        if (!file.exists() || file.length() <= 0) {//El archivo está vacío o no existe.
            createFileWithDefaultRooms();
        } else {
            searchEachRoom();
        }
        System.out.println("\n\n\t\tCSV REVISADO CORRECTAMENTE\n\n");
    }

    public void searchEachRoom() {
        Set<String> habitacionesBuscadas = new HashSet<>();//{ref3}
        Set<String> habitacionesEncontradas = new HashSet<>();
        for (int i = 1; i <= 20; i++) {
            habitacionesBuscadas.add(String.format("Room%02d", i));//{ref2}
        }
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {//{ref4}
                for (String room : habitacionesBuscadas) {
                    if (line.contains(room)) {
                        habitacionesEncontradas.add(room);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Resultado
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {//Se intenta escribir el objeto en un archivo{ref1}
            for (String room : habitacionesBuscadas) {
                if (!habitacionesEncontradas.contains(room)) {//habitacion no encontrada.
                    saveCSV(bw, roomsMap.get(room));
                }
            }
        } catch (IOException e) {//Error del tipo, entrada y salida de datos
            System.out.println("Error al guardar el archivo CSV" + e.getMessage());//Si ocurre error se imprime el texto y la descripción del error ocurrido
        }
    }

    public void saveCSV(BufferedWriter bw, Rooms roomForSubmit) throws IOException {//Método para guardar la información en un archivo CSV
        bw.write(roomForSubmit.getRoomName() + "|"
                + roomForSubmit.isAvailability() + "|"
                + roomForSubmit.getCapacity() + "|"
                + roomForSubmit.getDateRangeReservations() + "|"
                + roomForSubmit.getExtraServices() + "|"
                + roomForSubmit.getPricePerNight() + "|"
                + roomForSubmit.getRoomSize() + "|"
                + roomForSubmit.getRoomType() + "|"
                + roomForSubmit.getServicesIncluded() + "");//La escritura del archivo se realiza usando concetaciones
        bw.newLine();
    }

    // Método para cargar las habitaciones con la información predefinida
    private void loadRoomsData() {
        roomsMap.put("Room01", new Rooms("Room01", true, "1 Huésped", "SN", "SN", 50, "3m x 5m", "Individual", "Wifi, Televisión, Escritorio"));
        roomsMap.put("Room02", new Rooms("Room02", true, "1 Huésped", "SN", "SN", 55, "3m x 6m", "Individual Premium", "Wifi, Televisión, Aire acondicionado"));
        roomsMap.put("Room03", new Rooms("Room03", true, "2 Huéspedes", "SN", "SN", 80, "4m x 5.5m", "Doble estándar", "Wifi, Televisión, Aire acondicionado"));
        roomsMap.put("Room04", new Rooms("Room04", true, "2 Huéspedes", "SN", "SN", 90, "4m x 6.25m", "Doble con balcón", "Wifi, Televisión, Aire acondicionado, Balcón"));
        roomsMap.put("Room05", new Rooms("Room05", true, "2 Huéspedes", "SN", "SN", 100, "4.5m x 6.25m", "Doble Premium", "Wifi, Televisión, Aire acondicionado, Balcón"));
        roomsMap.put("Room06", new Rooms("Room06", true, "2 Huéspedes", "SN", "SN", 75, "4m x 5m", "Doble estándar", "Wifi, Televisión"));
        roomsMap.put("Room07", new Rooms("Room07", true, "3 Huéspedes", "SN", "SN", 120, "5m x 7m", "Triple con balcón", "Wifi, Televisión, Aire acondicionado, Balcón"));
        roomsMap.put("Room08", new Rooms("Room08", true, "3 Huéspedes", "SN", "SN", 110, "4.5m x 6.5m", "Triple estándar", "Wifi, Televisión, Aire acondicionado"));
        roomsMap.put("Room09", new Rooms("Room09", true, "3 Huéspedes", "SN", "SN", 130, "5.5m x 7m", "Triple Premium", "Wifi, Televisión, Aire acondicionado, Cesta de Bienvenida"));
        roomsMap.put("Room10", new Rooms("Room10", true, "4 Huéspedes", "SN", "SN", 150, "6m x 7.5m", "Cuádruple familiar", "Wifi, Televisión, Aire acondicionado"));
        roomsMap.put("Room11", new Rooms("Room11", true, "4 Huéspedes", "SN", "SN", 165, "6m x 8.5m", "Cuádruple con balcón", "Wifi, Televisión, Aire acondicionado, Balcón"));
        roomsMap.put("Room12", new Rooms("Room12", true, "4 Huéspedes", "SN", "SN", 160, "6m x 8m", "Cuádruple Premium", "Wifi, Televisión, Aire acondicionado, Balcón, Cesta de Bienvenida"));
        roomsMap.put("Room13", new Rooms("Room13", true, "4 Huéspedes", "SN", "SN", 140, "5.5m x 7.5m", "Cuádruple estándar", "Wifi, Televisión"));
        roomsMap.put("Room14", new Rooms("Room14", true, "4 Huéspedes", "SN", "SN", 145, "5.5m x 7.5m", "Cuádruple familiar", "Wifi, Televisión, Aire acondicionado"));
        roomsMap.put("Room15", new Rooms("Room15", true, "5 Huéspedes", "SN", "SN", 180, "6.5m x 8.5m", "Suite familiar", "Wifi, Televisión, Aire acondicionado, Cesta de Bienvenida"));
        roomsMap.put("Room16", new Rooms("Room16", true, "5 Huéspedes", "SN", "SN", 200, "7m x 8.5m", "Suite Premium", "Wifi, Televisión, Aire acondicionado, Balcón, Escritorio"));
        roomsMap.put("Room17", new Rooms("Room17", true, "5 Huéspedes", "SN", "SN", 175, "6.5m x 8m", "Suite estándar", "Wifi, Televisión, Aire acondicionado"));
        roomsMap.put("Room18", new Rooms("Room18", true, "5 Huéspedes", "SN", "SN", 190, "6.5m x 9m", "Suite con balcón", "Wifi, Televisión, Aire acondicionado, Balcón"));
        roomsMap.put("Room19", new Rooms("Room19", true, "5 Huéspedes", "SN", "SN", 185, "6.5m x 8.5m", "Suite Deluxe", "Wifi, Televisión, Aire acondicionado, Balcón"));
        roomsMap.put("Room20", new Rooms("Room20", true, "5 Huéspedes", "SN", "SN", 210, "7m x 9.5m", "Suite Master", "Wifi, Televisión, Aire acondicionado, Balcón, Escritorio"));
    }
}

/*
    ref1: se le agrega el segundo parámetro "true" para que agregue la información y no se sobrescriba el documento, al colocar "false" se sobreescribe el documento
    ref2: Genera Room01, Room02, ..., Room20. %02d: Formatea el número para que siempre tenga dos dígitos (e.g., 01, 02).
    ref3: Set es una interfaz en Java que representa un conjunto de elementos no repetidos. HashSet es una clase que implementa la interfaz Set. Utiliza una estructura de tabla hash para almacenar los elementos.
    ref4: Por cada línea leída, se verifica si contiene alguna de las habitaciones usando un bucle interno sobre las habitaciones buscadas. Si se encuentra una coincidencia, esa habitación se agrega a un conjunto (habitacionesEncontradas).
 */
