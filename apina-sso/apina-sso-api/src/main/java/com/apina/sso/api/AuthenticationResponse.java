package com.apina.sso.api;

/**
 *
 */
public class AuthenticationResponse {
    protected AuthenticationEnum status;
    protected String token;

    public AuthenticationResponse(AuthenticationEnum status, String token) {
        this.status = status;
        this.token = token;
    }
}
