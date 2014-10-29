package com.kari.test;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.kari.services.MongoDbService;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.UnknownHostException;

/**
 * Created by Kari on 27/10/14.
 */
@Singleton
public class TestServlet extends HttpServlet {

    @Inject
    MongoDbService mongoDbService;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("<html>");
        response.getWriter().println("<b>TestServlet</b><br>");

        if (request.getParameter("close") != null) {
            mongoDbService.closeConnection();

        } else if (request.getParameter("listimages") != null) {
            DB db = mongoDbService.getMongoDb();

            GridFS gfsPhoto = new GridFS(db, "testImagesCollection");
            DBCursor cursor = gfsPhoto.getFileList();
            while (cursor.hasNext()) {
                GridFSDBFile item = (GridFSDBFile)cursor.next();

                //response.getWriter().println("<a href=\"testservlet?image=" + item.getId() + "\">" + item.getFilename() + "</a><br>");
                response.getWriter().println("<img src=\"imageservlet?image=" + item.getId() + "\"/>");
            }
        }
        response.getWriter().println("</html>");
    }
}
