package com.artmal.utils;

public class ValidationException extends Exception {
    public ValidationException() {
        super();
    }

    public ValidationException(String message) {
        super("Validation fails on" + message);
    }
}
