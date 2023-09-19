package com.splitwise.service;

import com.splitwise.dto.response.ApiResponse;
import com.splitwise.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.UUID;


public interface UserService {
    User createUser(User user);

    ApiResponse<Object> getAllUsers();
    ApiResponse<Object> findUserById(String id);

    UserDetails findByEmail(String username);
}
