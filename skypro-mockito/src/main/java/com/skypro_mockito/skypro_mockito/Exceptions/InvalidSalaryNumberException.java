package com.skypro_mockito.skypro_mockito.Exceptions;

public class InvalidSalaryNumberException extends RuntimeException {
    public InvalidSalaryNumberException(Exception e) {
        super(e);
    }

    public InvalidSalaryNumberException(String message) {
        System.out.println(message);
    }

    public InvalidSalaryNumberException() {
        System.out.println("Invalid Salary number");
    }

}