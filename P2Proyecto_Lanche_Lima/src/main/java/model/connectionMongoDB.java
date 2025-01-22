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

    public List<Document> searchSelector(Bson filter) {
        List<Document> results = new ArrayList<>();
        try {
            getCollection().find(filter).into(results);
        } catch (MongoException e) {
            System.out.println("\n\n--->>>> Error en la búsqueda: " + e.getMessage() + " <<<<----\n\n");
            e.printStackTrace();
        }
        return results;
    }
}