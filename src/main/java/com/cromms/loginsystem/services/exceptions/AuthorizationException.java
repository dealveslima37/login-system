package com.cromms.loginsystem.services.exceptions;

public class AuthorizationException extends  RuntimeException{

    public AuthorizationException(String msg){
        super(msg);
    }

}
