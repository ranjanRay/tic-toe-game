package com.rajeev.exceptions;

public class InvalidPlayerException extends RuntimeException{
    public InvalidPlayerException(String message) {
        super(message);
    }
}
