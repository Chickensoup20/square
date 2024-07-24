package net.chicken.projects.square.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class MongoDB {
    public MongoClient client;
    public MongoDatabase db;
    public MongoCollection<Document> collection;
    public MongoDB(String connectionString){
        client = MongoClients.create(connectionString);
        db = client.getDatabase("db");
        collection = db.getCollection("savedVals");
    }

    public void saveInt(String name, int value) {
        Document query = new Document();
        query.put("name", name);

        Document newDocument = new Document();
        newDocument.put("name", name);
        newDocument.put("value", value);

        Document updateObject = new Document();
        updateObject.put("$set", newDocument);

        UpdateOptions options = new UpdateOptions().upsert(true);
        collection.updateOne(query, updateObject, options);
    }

    public int getInt(String name){
        Document doc = collection.find(new Document("name", name)).first();
        if (doc == null) {
            return 0;
        } else {
            return doc.getInteger("value");
        }
    }


}
