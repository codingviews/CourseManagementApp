package com.codingviews.services.user;

import com.codingviews.services.user.model.User;
import com.codingviews.services.user.validator.UserValidator;
import com.codingviews.services.user.validator.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserManagementConfig {
    @Bean
    public Validator<User> userValidator() {
        return new UserValidator();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
