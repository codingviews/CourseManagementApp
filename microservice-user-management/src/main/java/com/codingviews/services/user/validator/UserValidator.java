package com.codingviews.services.user.validator;

import com.codingviews.services.user.model.User;

public class UserValidator implements Validator<User> {
    @Override
    public void validate(User user) {
        if (user == null) {
            throw new ValidationException("User cannot be null");
        }

        if (user.getName() == null) {
            throw new ValidationException("User Name cannot be null");
        }

        if (user.getPassword() == null) {
            throw new ValidationException("User Password cannot be null");
        }
    }
}
