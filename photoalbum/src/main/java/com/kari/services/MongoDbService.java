package com.kari.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.mongodb.MongoClient;
import com.mongodb.DB;

import java.net.UnknownHostException;

/**
 * Created by Kari on 28/10/14.
 */
@Singleton
public class MongoDbService {

    private MongoClient mongoClient = null;
    private DB mongoDb = null;

    @Inject(optional = true)
    @Named("mongodb.host")
    private String dbHost = "localhost";

    @Inject(optional = true)
    @Named("mongodb.port")
    private int dbPort = 27017;

    @Inject(optional = true)
    @Named("mongodb.dbname")
    private String dbName = "testdb";

    public void closeConnection() {
        if (mongoClient != null) {
            System.out.println("Closing MongoDB connection...");
            mongoClient.close();
        }
    }

    public DB getMongoDb() {
        if (mongoDb == null) {
            mongoDb = getMongoClient().getDB( dbName );
        }
       return mongoDb;
    }

    public MongoClient getMongoClient() {
        if (mongoClient == null) {
            try {
                mongoClient = new MongoClient( dbHost, dbPort );
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }

        return mongoClient;
    }
}
