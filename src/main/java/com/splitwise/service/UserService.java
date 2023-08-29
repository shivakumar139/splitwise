package com.splitwise.service;

import com.splitwise.entity.User;

import java.util.List;

public interface UserService {
    String addUser(User user);

    List<User> getAllUsers();
}
