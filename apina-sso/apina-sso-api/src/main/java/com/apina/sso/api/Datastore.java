package com.apina.sso.api;

import java.util.Map;

/**
 *
 */
public interface Datastore {
    AuthenticationResponse authenticate(String username, String password) throws Exception;
    boolean logout(String username, String token) throws Exception;
    Map<String, String> attributes(String username, String token) throws Exception;
    void configure(String configuration) throws Exception;
}
