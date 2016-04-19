package com.apina.sso.datastores;

import com.apina.sso.api.AbstractDatastore;
import com.apina.sso.api.AuthenticationResponse;

import java.util.Map;

/**
 *
 */
public class JdbcDatastore extends AbstractDatastore {
    @Override
    public void configureDatastore(String configuration) throws Exception {

    }

    @Override
    public AuthenticationResponse authenticateUser(String username, String password) throws Exception {
        return null;
    }

    @Override
    public boolean logoutUser(String username, String token) throws Exception {
        return false;
    }

    @Override
    public Map<String, String> getUserAttributes(String username, String token) throws Exception {
        return null;
    }
}
