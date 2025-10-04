package com.example.exception;

/**
 * Custom unchecked exception when employee is not found
 */
public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException() {
        super("Employee not found in the company.");
    }

    public EmployeeNotFoundException(String message) {
        super(message);
    }
}