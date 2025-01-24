package model;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

public class connectionMongoDB {

    private final String dataBaseName = "ReservasHoteles";
    private final MongoClient client;
    private final MongoDatabase mongoDB;
    public static String collectionName = "usuarios";

    public connectionMongoDB() {
        client = MongoClients.create("mongodb://localhost:27017");
        mongoDB = client.getDatabase(dataBaseName);
        if (mongoDB.getCollection(collectionName) == null) {
            mongoDB.createCollection(collectionName);
        }
    }

    public MongoDatabase getMongoDB() {
        return mongoDB;
    }

    public MongoCollection<Document> getCollection() {
        return mongoDB.getCollection(collectionName);
    }

    public MongoDatabase createConnection() {
        try {
            System.out.println("\n\n--->>>> Conexión exitosa a la base de datos MongoDB <<<<----\n\n");
            return mongoDB;
        } catch (Exception e) {
            System.out.println("\n\n--->>>> Error al conectar a la base de datos MongoDB: " + e.getMessage() + " <<<<----\n\n");
            e.printStackTrace();
        }
        return null;
    }

    public boolean insertUsuario(Document usuario) {
        try {
            getCollection().insertOne(usuario);
            return true;
        } catch (MongoException e) {
            System.out.println("\n\n--->>>> Error al insertar usuario: " + e.getMessage() + " <<<<----\n\n");
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Document> searchDocument(Document filtro) {
        MongoDatabase db = createConnection();
        try {
            MongoCollection<Document> collection = db.getCollection(collectionName);
            ArrayList resultados = new ArrayList<>();
            for (Document doc : collection.find(filtro)) {
                resultados.add(doc);
            }
            System.out.println("\n\n--->>>DATOS BUSCADOS EN LA DB CON EXITO<<<---\n\n");
            return resultados;
        } catch (MongoException e) {
            System.out.println("\n\n--->>>ERROR AL BUSCAR EN LA DB<<<---\n\n");
            e.printStackTrace();
        }
        return null;
    }
}
