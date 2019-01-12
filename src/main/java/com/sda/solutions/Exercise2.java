package com.sda.solutions;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.sda.bios.Utils;
import org.bson.Document;

import java.util.function.Consumer;

public class Exercise2 {
    public static void main(String[] args) {
        try(MongoClient client = Utils.connect()) {
            MongoDatabase db = client.getDatabase("bios");
            MongoCollection connection = db.getCollection("bios");
            connection.find(Filters.eq("name.first", "John"))
                    .forEach((Consumer<Document>) Exercise2::printDocument);
        }
    }

    public static void printDocument(Document doc) {
        Document name = doc.get("name", Document.class);
        System.out.println(name.getString("first") + " " + name.getString("last"));
    }
}
