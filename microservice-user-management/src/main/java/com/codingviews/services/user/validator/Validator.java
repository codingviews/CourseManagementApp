package com.codingviews.services.user.validator;

@FunctionalInterface
public interface Validator<T> {
    void validate(T model) throws ValidationException;
}
