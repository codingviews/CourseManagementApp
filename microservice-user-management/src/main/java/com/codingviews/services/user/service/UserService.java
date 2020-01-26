package com.codingviews.services.user.service;

import com.codingviews.services.user.model.User;

import java.util.List;

public interface UserService {
    User save(User user);

    User findByUserName(String userName);

    List<String> findUsers(List<Long> userIdList);
}
