package com.fireDetection.cot.Exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    String message;
    public UserAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}