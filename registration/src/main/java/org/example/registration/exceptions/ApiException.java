package org.example.registration.exceptions;

public abstract class ApiException extends RuntimeException{
    public ApiException(String message){
        super(message);
    }

}
