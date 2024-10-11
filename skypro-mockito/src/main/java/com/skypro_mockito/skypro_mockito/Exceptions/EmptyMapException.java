package com.skypro_mockito.skypro_mockito.Exceptions;

public class EmptyMapException extends RuntimeException {
    public EmptyMapException(Exception e) {
        super(e);
    }

    public EmptyMapException(String message) {
        System.out.println(message);
    }

    public EmptyMapException() {
        System.out.println("Empty map");
    }
}
