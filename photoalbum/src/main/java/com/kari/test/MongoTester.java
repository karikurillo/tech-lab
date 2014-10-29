package com.kari.test;

import com.mongodb.*;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

import java.io.File;
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

        //DBObject user = createUser(col, "admin", "System Admin", "password");

        //System.out.println(user);
        System.out.println("user count: " + col.getCount());

        //col = db.getCollection("testImagesCollection");
        //saveImage(db, "/Users/Kari/projects/git-repos/tech-lab/photoalbum/src/main/resources/images/spongebob.jpg");
        //saveImage(db, "/Users/Kari/projects/git-repos/tech-lab/photoalbum/src/main/resources/images/spongebob-2.jpg");
        listImages(db);
        //readImage(db);

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

    public static void saveImage(DB db, String path) {
        try {
            File imageFile = new File(path);
            GridFS gfsPhoto = new GridFS(db, "testImagesCollection");
            GridFSInputFile gfsFile = gfsPhoto.createFile(imageFile);
            gfsFile.setFilename(imageFile.getName());
            gfsFile.put("user", "admin");
            gfsFile.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readImage(DB db) {
        String filename = "spongebob.jpg";
        GridFS gfsPhoto = new GridFS(db, "testImagesCollection");
        //gfsPhoto.remove(gfsPhoto.findOne(filename));
        //GridFSDBFile imageForOutput = gfsPhoto.findOne(filename);
        //System.out.println(imageForOutput);
    }

    public static void listImages(DB db) {
        GridFS gfsPhoto = new GridFS(db, "testImagesCollection");
        DBCursor cursor = gfsPhoto.getFileList();
        System.out.println("image count: " + cursor.size());
        while (cursor.hasNext()) {
            System.out.println("  " + cursor.next());
        }
    }
}
