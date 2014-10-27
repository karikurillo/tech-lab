package com.kari.test;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Kari on 27/10/14.
 */
@Singleton
public class TestServlet extends HttpServlet {

    @Inject
    ImageReader imageReader;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("Service: " + imageReader.sayMyName());
    }
}
