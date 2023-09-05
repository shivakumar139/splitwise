package com.splitwise.service;

import com.splitwise.dto.response.ApiResponse;
import com.splitwise.entity.User;

import java.util.UUID;

public interface UserService {
    ApiResponse<Object> createUser(User user);

    ApiResponse<Object> getAllUsers();
    ApiResponse<Object> findUserById(UUID id);
}
