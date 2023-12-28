package com.example.demospring3.exception;

public class ModeloNotFoundException extends RuntimeException {
    public ModeloNotFoundException(String message) {
        super(message);
    }
}
