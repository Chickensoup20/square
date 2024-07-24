package net.chicken.projects.square.plotmanagement;



import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;
import org.bukkit.Bukkit;

import java.util.UUID;

import static net.chicken.projects.square.Square.db;

public class Plot {
    int plotID;
    String plotName;
    UUID ownerUuid;
    PlotTypes plotType;
    MongoCollection<Document> collection = db.db.getCollection("plots");
    Document plotDoc;

    public Plot( UUID ownerUuid, PlotTypes plotType) {
        plotID = db.getInt("plotID");
        db.saveInt("plotID", plotID + 1);
        plotDoc = new Document();
        plotDoc.put("id", plotID);
        plotDoc.put("owner", ownerUuid.toString());
        plotDoc.put("plotName", Bukkit.getOfflinePlayer(ownerUuid).getName() + "'s Plot");
        plotDoc.put("plotType", plotType.toString());
        collection.insertOne(plotDoc);
        WorldManager manager = new WorldManager();
        manager.createWorld(plotID + "");
    }
    public String getName(){
        return plotDoc.getString("plotName");
    }
    public UUID getOwnerUuid(){
        return UUID.fromString(plotDoc.getString("owner"));
    }

    public void setOwner(String playerName){
        setDocData("owner",Bukkit.getOfflinePlayer(playerName).getUniqueId().toString());
    }
    public void setName(String name){
        setDocData("plotName",name);
    }

    private void setDocData(String key, Object value){
        Document query = new Document();
        query.put("id", plotID);

        Document newDocument = new Document();
        newDocument.put(key, value);

        Document updateObject = new Document();
        updateObject.put("$set", newDocument);

        UpdateOptions options = new UpdateOptions().upsert(true);
        collection.updateOne(query, updateObject, options);
    }
}
