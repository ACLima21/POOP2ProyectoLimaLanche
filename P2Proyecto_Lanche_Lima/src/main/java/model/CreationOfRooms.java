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

    // Método para cargar las habitaciones con la información predefinida
    private void loadRoomsData() {
        roomsMap.put("Room01", new Rooms("Room01", false, 1, "SN", "SN", 50, "3m x 5m", "Individual", "Wifi, Televisión, Escritorio"));
        roomsMap.put("Room02", new Rooms("Room02", false, 1, "SN", "SN", 55, "3m x 6m", "Individual Premium", "Wifi, Televisión, Aire acondicionado"));
        roomsMap.put("Room03", new Rooms("Room03", false, 2, "SN", "SN", 80, "4m x 5.5m", "Doble estándar", "Wifi, Televisión, Aire acondicionado"));
        roomsMap.put("Room04", new Rooms("Room04", false, 2, "SN", "SN", 90, "4m x 6.25m", "Doble con balcón", "Wifi, Televisión, Aire acondicionado, Balcón"));
        roomsMap.put("Room05", new Rooms("Room05", false, 2, "SN", "SN", 100, "4.5m x 6.25m", "Doble Premium", "Wifi, Televisión, Aire acondicionado, Balcón"));
        roomsMap.put("Room06", new Rooms("Room06", false, 2, "SN", "SN", 75, "4m x 5m", "Doble estándar", "Wifi, Televisión"));
        roomsMap.put("Room07", new Rooms("Room07", false, 3, "SN", "SN", 120, "5m x 7m", "Triple con balcón", "Wifi, Televisión, Aire acondicionado, Balcón"));
        roomsMap.put("Room08", new Rooms("Room08", false, 3, "SN", "SN", 110, "4.5m x 6.5m", "Triple estándar", "Wifi, Televisión, Aire acondicionado"));
        roomsMap.put("Room09", new Rooms("Room09", false, 3, "SN", "SN", 130, "5.5m x 7m", "Triple Premium", "Wifi, Televisión, Aire acondicionado, Cesta de Bienvenida"));
        roomsMap.put("Room10", new Rooms("Room10", false, 4, "SN", "SN", 150, "6m x 7.5m", "Cuádruple familiar", "Wifi, Televisión, Aire acondicionado"));
        roomsMap.put("Room11", new Rooms("Room11", false, 4, "SN", "SN", 165, "6m x 8.5m", "Cuádruple con balcón", "Wifi, Televisión, Aire acondicionado, Balcón"));
        roomsMap.put("Room12", new Rooms("Room12", false, 4, "SN", "SN", 160, "6m x 8m", "Cuádruple Premium", "Wifi, Televisión, Aire acondicionado, Balcón, Cesta de Bienvenida"));
        roomsMap.put("Room13", new Rooms("Room13", false, 4, "SN", "SN", 140, "5.5m x 7.5m", "Cuádruple estándar", "Wifi, Televisión"));
        roomsMap.put("Room14", new Rooms("Room14", false, 4, "SN", "SN", 145, "5.5m x 7.5m", "Cuádruple familiar", "Wifi, Televisión, Aire acondicionado"));
        roomsMap.put("Room15", new Rooms("Room15", false, 5, "SN", "SN", 180, "6.5m x 8.5m", "Suite familiar", "Wifi, Televisión, Aire acondicionado, Cesta de Bienvenida"));
        roomsMap.put("Room16", new Rooms("Room16", false, 5, "SN", "SN", 200, "7m x 8.5m", "Suite Premium", "Wifi, Televisión, Aire acondicionado, Balcón, Escritorio"));
        roomsMap.put("Room17", new Rooms("Room17", false, 5, "SN", "SN", 175, "6.5m x 8m", "Suite estándar", "Wifi, Televisión, Aire acondicionado"));
        roomsMap.put("Room18", new Rooms("Room18", false, 5, "SN", "SN", 190, "6.5m x 9m", "Suite con balcón", "Wifi, Televisión, Aire acondicionado, Balcón"));
        roomsMap.put("Room19", new Rooms("Room19", false, 5, "SN", "SN", 185, "6.5m x 8.5m", "Suite Deluxe", "Wifi, Televisión, Aire acondicionado, Balcón"));
        roomsMap.put("Room20", new Rooms("Room20", false, 5, "SN", "SN", 210, "7m x 9.5m", "Suite Master", "Wifi, Televisión, Aire acondicionado, Balcón, Escritorio"));
    }

    public void createFileWithHeader() {//Método para guardar la información en un archivo CSV
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
        } catch (IOException e) {//Error del tipo, entrada y salida de datos
            System.out.println("Error al guardar el archivo CSV" + e.getMessage());//Si ocurre error se imprime el texto y la descripción del error ocurrido
        }
    }

    public void confimTheFileExists() {
        // Verificar si el archivo existe y no está vacío
        File file = new File(fileName);
        if (!file.exists() || file.length() <= 0) {//El archivo está vacío o no existe.
            createFileWithHeader();
        }
        searchEachRoom();
    }

    public void searchEachRoom() {//Método que lee archivo tanto JSON como CSV
        Set<String> habitacionesBuscadas = new HashSet<>();//{ref3}
        for (int i = 1; i <= 20; i++) {
            habitacionesBuscadas.add(String.format("Room%02d", i));//{ref2}
        }

        Set<String> habitacionesEncontradas = new HashSet<>();
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
        for (String room : habitacionesBuscadas) {
            if (!habitacionesEncontradas.contains(room)) {//habitacion no encontrada.
                saveCSV(roomsMap.get(room));
            }
        }
    }

    public void searchIfDocumentIsOK() {
        String line = "Id:678999|Name:Juan|Age:32";
        Pattern pattern = Pattern.compile("Name:([^|]*)"); // Busca "Name:" seguido de cualquier cosa menos "|"
        Matcher matcher = pattern.matcher(line);

        String name = "";
        if (matcher.find()) {
            name = matcher.group(1); // Captura lo que está entre "Name:" y "|"
        }

        System.out.println("Nombre: " + name); // Salida: Nombre: Juan
    }

    public void saveCSV(Rooms roomForSubmit) {//Método para guardar la información en un archivo CSV
        Rooms rooms = roomForSubmit;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {//Se intenta escribir el objeto en un archivo{ref1}
            //String roomName, boolean availability, int capacity, String dateRangeReservations,String extraServices, double pricePerNight, String roomSize, String roomType, String servicesIncluded
            bw.write(rooms.getRoomName() + "|"
                    + rooms.isAvailability() + "|"
                    + rooms.getCapacity() + "|"
                    + rooms.getDateRangeReservations() + "|"
                    + rooms.getExtraServices() + "|"
                    + rooms.getPricePerNight() + "|"
                    + rooms.getRoomSize() + "|"
                    + rooms.getRoomType() + "|"
                    + rooms.getServicesIncluded() + "");//La escritura del archivo se realiza usando concetaciones
        } catch (IOException e) {//Error del tipo, entrada y salida de datos
            System.out.println("Error al guardar el archivo JSON" + e.getMessage());//Si ocurre error se imprime el texto y la descripción del error ocurrido
        }
    }
}

/*
    ref1: se le agrega el segundo parámetro "true" para que agregue la información y no se sobrescriba el documento, al colocar "false" se sobreescribe el documento
    ref2: Genera Room01, Room02, ..., Room20. %02d: Formatea el número para que siempre tenga dos dígitos (e.g., 01, 02).
    ref3: Set es una interfaz en Java que representa un conjunto de elementos no repetidos. HashSet es una clase que implementa la interfaz Set. Utiliza una estructura de tabla hash para almacenar los elementos.
    ref4: Por cada línea leída, se verifica si contiene alguna de las habitaciones usando un bucle interno sobre las habitaciones buscadas. Si se encuentra una coincidencia, esa habitación se agrega a un conjunto (habitacionesEncontradas).
 */
