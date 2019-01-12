package com.sda.solutions;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.sda.bios.Utils;
import org.bson.Document;

import java.util.List;
import java.util.function.Consumer;

public class Exercise5 {
    public static void main(String[] args) {
        try(MongoClient client = Utils.connect()) {
            MongoDatabase db = client.getDatabase("bios");
            MongoCollection bios = db.getCollection("bios");
            bios.find(Filters.not(Filters.exists("contribs.3")))
                    .forEach((Consumer<Document>) Exercise5::printDocument);
        }
    }

    public static void printDocument(Document doc) {
        Document name = doc.get("name", Document.class);
        System.out.println(name.getString("first") + " "
                + name.getString("last"));
        List<String> contribs = doc.get("contribs", List.class);
        contribs.forEach(contrib -> System.out.println("- " + contrib));
    }
}
