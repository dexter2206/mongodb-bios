package com.sda.bios;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.function.Consumer;

public class GetAllRecords {

    public static void main(String[] args) {
        try(MongoClient client = Utils.connect()) {
            MongoDatabase db = client.getDatabase("bios");
            MongoCollection<Document> collection = db.getCollection("bios");
            collection.find().forEach((Consumer<Document>) System.out::println);
        }
    }
}
