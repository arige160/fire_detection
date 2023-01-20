package com.fireDetection.cot.Exceptions;

public class UserForbiddenException extends RuntimeException{
    String message;
    public UserForbiddenException(String message) {
        super(message);
        this.message = message;
    }
}