package com.sda.solutions;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.sda.bios.Utils;
import org.bson.Document;

import java.util.function.Consumer;

public class Exercise1 {

    public static void main(String[] args) {
        try(MongoClient client = Utils.connect()) {
            MongoDatabase db = client.getDatabase("bios");
            MongoCollection bios = db.getCollection("bios");
            bios.find()
                    .projection(Projections.include("name.first", "name.last"))
                    .forEach((Consumer<Document>) Exercise1::printDocument);
        }
    }

    public static void printDocument(Document doc) {
        Document name = doc.get("name", Document.class);
        System.out.println(name.getString("first") + " " + name.getString("last"));
    }
}
