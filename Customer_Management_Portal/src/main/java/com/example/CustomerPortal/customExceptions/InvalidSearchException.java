package com.example.CustomerPortal.customExceptions;

public class InvalidSearchException extends RuntimeException{
    public InvalidSearchException(String message) {
        super(message);
    }
}
