package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CreationOfRooms {

    private String fileName = "RoomsCSVLancheLima.csv";//nombre del archivo

    public void createFileWithHeader() {//Método para guardar la información en un archivo CSV

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {//Se intenta escribir el objeto en un archivo{ref1}
            bw.write("Availability|"
                    + "Capacity|"
                    + "DateRangeReservations|"
                    + "ExtraServices|"
                    + "PricePerNight|"
                    + "RoomName|"
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
                for (String habitacion : habitacionesBuscadas) {
                    if (line.contains(habitacion)) {
                        habitacionesEncontradas.add(habitacion);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Resultado
        for (String habitacion : habitacionesBuscadas) {
            if (!habitacionesEncontradas.contains(habitacion)) {//habitacion no encontrada.

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

    public void saveCSV() {//Método para guardar la información en un archivo CSV

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {//Se intenta escribir el objeto en un archivo{ref1}
            //La escritura del archivo se realiza usando concetaciones y llamando los datos a través de los getters:
            bw.write("Título, ");
            bw.write(getTitle() + "| ");
            bw.write("Autor, ");
            bw.write(getAuthor() + "| ");
            bw.write("ISBN, ");
            bw.write(getISBN() + "| ");
            bw.write("URL, ");
            bw.write(getDonwloadUrl() + ", ");
            bw.write("Formato, ");
            bw.write(getFileFormat() + ", ");
            bw.write("Número de páginas, ");
            bw.write(String.valueOf(getNumPages())/*se usa para convertir de numérico a String*/);
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
