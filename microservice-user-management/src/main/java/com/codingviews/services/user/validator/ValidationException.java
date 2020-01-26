package com.codingviews.services.user.validator;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
