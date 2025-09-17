package com.rajeev.exceptions;

public class TooManyBotsException extends RuntimeException{
    public TooManyBotsException(String message) {
        super(message);
    }
}
