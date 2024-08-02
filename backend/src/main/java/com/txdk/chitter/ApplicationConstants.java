package com.txdk.chitter;

public class ApplicationConstants {

    private ApplicationConstants() {
        throw new IllegalStateException("Constants class should not be instantiated");
    }
    
    public static final String USER_ENDPOINT_PREFIX = "/user";
    public static final String POST_ENDPOINT_PREFIX = "/posts";
    public static final String FRONTEND_SERVER_PORT = ":3000";
    public static final String REGISTRATION_ENDPOINT = "/register";
    public static final String LOGIN_ENDPOINT = "/login";
    public static final String AUTHENTICATION_ENDPOINT = "/authenticate";

    public static final int TOKEN_EXPIRATION = 3600000;
    public static final String SECRET_KEY = System.getenv("SECRET_KEY");

}
