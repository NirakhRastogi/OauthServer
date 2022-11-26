package com.oauth.server.OauthServerDemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class InvalidCredentialsException extends RuntimeException {

    private String message;

    public InvalidCredentialsException(String message){
        super(message, null);
    }

    public InvalidCredentialsException(String message, Exception e){
        super(message, e);
    }

}