package com.artmal.utils;

/**
 * Exception for server-side validation.
 * @author Artem Malchenko
 */
public class ValidationException extends Exception {
    public ValidationException() {
        super();
    }

    public ValidationException(String message) {
        super("Validation fails on" + message);
    }
}
