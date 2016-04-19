package com.apina.sso.datastores;

import com.apina.sso.api.AbstractDatastore;
import com.apina.sso.api.AuthenticationEnum;
import com.apina.sso.api.AuthenticationResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 */
public class FileDatastore extends AbstractDatastore {

    class User {
        public String uid;
        public String password;
        public String fullName;
        public Set<String> groups;
        public Set<String> roles;
    }

    protected Map<String, User> users = new HashMap<String, User>();

    @Override
    public void configureDatastore(String configuration) throws Exception {
        // Read and parse data file


    }

    @Override
    public AuthenticationResponse authenticateUser(String username, String password) throws Exception {
        return new AuthenticationResponse(AuthenticationEnum.USER_NOT_FOUND);
    }

    @Override
    public boolean logoutUser(String username, String token) throws Exception {
        return true;
    }

    @Override
    public Map<String, String> getUserAttributes(String username, String token) throws Exception {
        return null;
    }
}
