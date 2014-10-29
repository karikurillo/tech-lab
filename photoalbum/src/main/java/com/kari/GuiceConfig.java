package com.kari;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.kari.services.MongoDbService;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

/**
 * Created by Kari on 27/10/14.
 */
@WebListener
public class GuiceConfig extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {

            return Guice.createInjector(
                    new WebServletModule()
            );
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Close DB connections
        Injector injector = (Injector) sce.getServletContext().getAttribute(Injector.class.getName());
        MongoDbService mongoDbService = injector.getInstance(MongoDbService.class);
        mongoDbService.closeConnection();
    }
}
