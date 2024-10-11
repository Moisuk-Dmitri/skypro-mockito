package com.skypro_mockito.skypro_mockito.Exceptions;

public class InvalidDepartmentNumberException extends RuntimeException {
    public InvalidDepartmentNumberException(Exception e) {
        super(e);
    }

    public InvalidDepartmentNumberException(String message) {
        System.out.println(message);
    }

    public InvalidDepartmentNumberException( ) {
        System.out.println("Invalid department number");
    }
}
