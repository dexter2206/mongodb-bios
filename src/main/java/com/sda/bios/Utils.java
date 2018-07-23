package com.sda.bios;

import com.mongodb.MongoClient;
import org.bson.Document;

import java.util.List;

public class Utils {

    public static MongoClient connect() {
        return new MongoClient("localhost", 27017);
    }
}
