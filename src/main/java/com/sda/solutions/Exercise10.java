package com.sda.solutions;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.sda.bios.Utils;
import org.bson.Document;

import java.util.List;
import java.util.function.Consumer;

public class Exercise10 {
    public static void main(String[] args) {
        try(MongoClient client = Utils.connect()) {
            MongoDatabase db = client.getDatabase("bios");
            MongoCollection bios = db.getCollection("bios");
            bios.find(Filters.elemMatch("awards", Filters.and(
                    Filters.eq("award", "Turing Award"), Filters.lt("year", 2000))))
                    .forEach((Consumer<Document>) Exercise10::printDocument);
        }
    }

    public static void printDocument(Document doc) {
        Document name = doc.get("name", Document.class);
        System.out.println(name.getString("first") + " "
                + name.getString("last"));
        List<Document> awards = doc.get("awards", List.class);
        awards.forEach(entry -> System.out.println("- " + entry.get("award")
                + " " + entry.get("year")));
    }
}
