package model;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class connectionMongoDB {
    private final String dataBaseName = "ReservasHoteles";
    private final MongoClient client;
    private MongoDatabase mongoDB;
    public static String collectionName = "reservas";

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

    public MongoDatabase createConnection() {
        try {
            mongoDB = getMongoDB();
            System.out.println("\n\n--->>>> Conexión exitosa a la base de datos MongoDB <<<<----\n\n");
            return mongoDB;
        } catch (Exception e) {
            System.out.println("\n\n--->>>> Error al conectar a la base de datos MongoDB: " + e.getMessage() + " <<<<----\n\n");
            e.printStackTrace();
        }
        return null;
    }

    public boolean insertReserva() {
        return false;
        }
    }


