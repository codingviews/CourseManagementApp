package com.codingviews.services.user.service;

import com.codingviews.services.user.model.User;
import com.codingviews.services.user.repository.UserRepository;
import com.codingviews.services.user.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final Validator<User> validator;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, Validator<User> validator, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.validator = validator;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(User user) {
        validator.validate(user);

        User userToSave = new User.Builder(user.getId())
                .name(user.getName())
                .userName(user.getUserName())
                .password(passwordEncoder.encode(user.getPassword()))
                .role(user.getRole())
                .build();

        return userRepository.save(userToSave);
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName).orElse(null);
    }

    @Override
    public List<String> findUsers(List<Long> userIdList) {
        return userRepository.findByIdList(userIdList);
    }
}
