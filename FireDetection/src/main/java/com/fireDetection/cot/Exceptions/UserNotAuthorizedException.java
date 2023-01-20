package com.fireDetection.cot.Exceptions;

public class UserNotAuthorizedException extends RuntimeException{
    String message;
    public UserNotAuthorizedException(String message) {
        super(message);
        this.message = message;
    }
}