package com.oauth.server.OauthServerDemo.config;


import java.security.Key;
import java.util.List;

public class Constants {

    public static final String X_USER_NAME = "X-USER-NAME";
    public static final String X_AUTHORIZATION = "X-AUTHORIZATION";
    public static final List<String> NO_AUTH_URLS = List.of("/user/signup", "/error", "/token");
    public static final List<String> ONLY_CLIENT_ID_URLS = List.of("/oauth/access-token");
    public static final long JWT_EXPIRE_TIME = 3l;

    public static final String X_BEARER_AUTH = "X-BEARER-AUTH";
    public static final String X_CLIENT_ID = "X-CLIENT-ID";
    public static final String X_LOCATION = "Location";
    public static final String X_SECRET = "X-SECRET";
    public static final String SERVICE_NAME = "OAUTH_SERVER";
    public static final String JWT_SECRET = "JWT_SECRET";
    public static final String REDIRECT_URL = "http://localhost:8081/login?ROLES=";
}