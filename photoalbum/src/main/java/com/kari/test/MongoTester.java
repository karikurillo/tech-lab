package com.kari.test;

import com.mongodb.*;

import java.net.UnknownHostException;

/**
 * Created by Kari on 27/10/14.
 */
public class MongoTester {

    public static void main(String[] args) {
        MongoClient mongoClient = null;
        DB db = null;

        try {
            mongoClient = new MongoClient( "localhost", 27017 );
            db = mongoClient.getDB( "testdb" );
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        for (String collection : db.getCollectionNames()) {
            System.out.println(collection);
        }

        DBCollection col = db.getCollection("userCollection");

        DBObject user = createUser(col, "admin", "System Admin", "password");

        System.out.println(user);
        System.out.println("user count: " + col.getCount());

        mongoClient.close();
    }

    public static DBObject createUser(DBCollection collection, String username, String fullname, String password) {
        BasicDBObject query = new BasicDBObject("username", username);
        DBCursor cursor = collection.find(query);
        DBObject user = null;
        if (cursor.hasNext()) {
            user = cursor.next();
        } else {
             user = new BasicDBObject("username", username)
                    .append("fullname", fullname)
                    .append("password", password);

            collection.insert(user);
        }
        return user;
    }

}
