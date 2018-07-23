package com.sda.bios;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Indexes;
import org.bson.Document;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class InsertBiosDocuments {


    public static void main(String[] args) throws IOException {
        try(MongoClient client = Utils.connect()) {
            MongoDatabase db = client.getDatabase("bios");
            MongoCollection<Document> products = db.getCollection("bios");
            products.createIndex(Indexes.compoundIndex(
                    Indexes.text("name"),
                    Indexes.text("awards")));
            String data = new String(Files.readAllBytes(Paths.get("./src/main/resources/data.json")), StandardCharsets.UTF_8);
            products.insertMany((List<Document>) Document.parse("{\"json\":" + data + "}").get("json"));
        }
    }
}
