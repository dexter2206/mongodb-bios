package com.sda.solutions;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.sda.bios.Utils;
import org.bson.Document;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

public class Exercise9 {
    public static void main(String[] args) throws ParseException {
        try(MongoClient client = Utils.connect()) {
            MongoDatabase db = client.getDatabase("bios");
            MongoCollection bios = db.getCollection("bios");
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            bios.find(Filters.lt("birth", format.parse("1960-01-01")))
                    .forEach((Consumer<Document>) Exercise9::printDocument);
        }
    }

    public static void printDocument(Document doc) {
        Document name = doc.get("name", Document.class);
        System.out.println(name.getString("first") + " "
                + name.getString("last"));
        System.out.println(" - born: " + doc.get("birth"));
    }
}
