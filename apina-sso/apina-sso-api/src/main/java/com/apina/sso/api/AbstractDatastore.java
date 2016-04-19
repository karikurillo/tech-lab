package com.apina.sso.api;

import java.util.Map;

/**
 *
 */
public abstract class AbstractDatastore implements Datastore {

    public abstract void configureDatastore(String configuration) throws Exception;
    public abstract AuthenticationResponse authenticateUser(String username, String password) throws Exception;
    public abstract boolean logoutUser(String username, String token) throws Exception;
    public abstract Map<String, String> getUserAttributes(String username, String token) throws Exception;

    public AuthenticationResponse authenticate(String username, String password) throws Exception {
        return authenticateUser(username, password);
    }

    public boolean logout(String username, String token) throws Exception {
        return logoutUser(username, token);
    }

    public Map<String, String> attributes(String username, String token) throws Exception {
        return getUserAttributes(username, token);
    }

    public void configure(String configuration) throws Exception {
        configureDatastore(configuration);
    }
}
