package com.kari;

import com.google.inject.servlet.ServletModule;
import com.kari.test.ImageReader;
import com.kari.test.TestServlet;

/**
 * Created by Kari on 27/10/14.
 */
public class WebModule extends ServletModule {

    @Override
    protected void configureServlets() {
        serve("testservlet").with(TestServlet.class);

        bind(ImageReader.class);
    }
}
