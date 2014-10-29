package com.kari;

import com.google.inject.Injector;
import com.google.inject.servlet.ServletModule;
import com.kari.services.MongoDbService;
import com.kari.test.ImageServlet;
import com.kari.test.TestServlet;
import org.omg.SendingContext.RunTime;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by Kari on 27/10/14.
 */
public class WebServletModule extends ServletModule {

    MongoDbService mongoDbService = null;

    @Override
    protected void configureServlets() {
        serve("/testservlet").with(TestServlet.class);
        serve("/imageservlet").with(ImageServlet.class);

        bind(MongoDbService.class).asEagerSingleton();

        /*
        mongoDbService = new MongoDbService();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                mongoDbService.closeConnection();
            }
        });
        bind(MongoDbService.class).toInstance(mongoDbService);
        */
        /*
        try {
            final Class<?> queueHolderClass =
                    Class.forName("com.google.inject.internal.util.$MapMaker$QueueHolder");
            final Field queueField = queueHolderClass.getDeclaredField("queue");
// make MapMaker.QueueHolder.queue accessible
            queueField.setAccessible(true);
// remove the final modifier from MapMaker.QueueHolder.queue
            final Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(queueField, queueField.getModifiers() & ~Modifier.FINAL);
// set it to null
            queueField.set(null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
    }


}
