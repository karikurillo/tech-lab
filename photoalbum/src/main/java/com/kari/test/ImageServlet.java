package com.kari.test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.kari.services.MongoDbService;
import com.mongodb.DB;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import org.bson.types.ObjectId;

/**
 * Created by Kari on 28/10/14.
 */
@Singleton
public class ImageServlet extends HttpServlet {

    @Inject
    MongoDbService mongoDbService;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DB db = mongoDbService.getMongoDb();
        String id = request.getParameter("image");

        GridFS gfsPhoto = new GridFS(db, "testImagesCollection");
        GridFSDBFile imageForOutput = gfsPhoto.findOne(new ObjectId(id));

        response.setHeader("Content-Type", getServletContext().getMimeType(imageForOutput.getFilename()));
        response.setHeader("Content-Length", String.valueOf(imageForOutput.getLength()));
        //response.setHeader("Content-Disposition", "inline; filename=\"" + image.getFilename() + "\"");

        BufferedInputStream input = null;
        BufferedOutputStream output = null;

        try {
            input = new BufferedInputStream(imageForOutput.getInputStream());
            output = new BufferedOutputStream(response.getOutputStream());
            byte[] buffer = new byte[8192];
            for (int length = 0; (length = input.read(buffer)) > 0;) {
                output.write(buffer, 0, length);
            }
        } finally {
            if (output != null) try { output.close(); } catch (IOException logOrIgnore) {}
            if (input != null) try { input.close(); } catch (IOException logOrIgnore) {}
        }
    }
}
