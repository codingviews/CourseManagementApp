package com.codingviews.services.user.controller;

import com.codingviews.services.user.model.Role;
import com.codingviews.services.user.model.User;
import com.codingviews.services.user.service.UserService;
import com.codingviews.services.user.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class UserController {
    private final UserService userService;
    private final Validator<User> userValidator;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, Validator<User> userValidator, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/service/registration")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        // Validate the user data
        userValidator.validate(user);

        // Check to see if the user already exists
        if (userService.findByUserName(user.getUserName()) != null) {
            // Status Code: 409
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        // Create a new User to save with encoded password and Role as USER.
        User userToSave = new User.Builder(user.getId())
                .name(user.getName())
                .userName(user.getUserName())
                .password(passwordEncoder.encode(user.getPassword()))
                .role(Role.USER)
                .build();

        return new ResponseEntity<>(userService.save(userToSave), HttpStatus.CREATED);
    }

    @GetMapping("/service/login")
    public ResponseEntity<?> getUser(Principal principal) {
        // Principal is from the Request i.e. Principal principal = request.getUserPrincipal();
        if (principal == null || principal.getName() == null) {
            // This mean, logout will be successful. login?logout
            return new ResponseEntity<>(HttpStatus.OK);
        }

        // username is from the principal i.e. username = principal.getName();
        return ResponseEntity.ok(userService.findByUserName(principal.getName()));
    }

    @PostMapping("/service/names")
    public ResponseEntity<?> getNamesOfUsers(@RequestBody List<Long> idList) {
        return ResponseEntity.ok(userService.findUsers(idList));
    }

    @GetMapping("/service/ping")
    public ResponseEntity<?> ping() {
        return ResponseEntity.ok("Ping Successful.");
    }
}
