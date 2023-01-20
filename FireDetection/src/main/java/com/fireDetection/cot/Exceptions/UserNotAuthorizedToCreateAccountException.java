package com.fireDetection.cot.Exceptions;

public class UserNotAuthorizedToCreateAccountException extends RuntimeException{
    String message;
    public UserNotAuthorizedToCreateAccountException(String message) {
        super(message);
        this.message = message;
    }
}