package com.splitwise.service.impl;

import com.splitwise.dto.response.ApiResponse;
import com.splitwise.entity.User;
import com.splitwise.exception.UserNotFoundException;
import com.splitwise.repository.UserRepository;
import com.splitwise.service.UserService;
import com.sun.jdi.InternalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;


@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public ApiResponse<Object> createUser(User user) {
        User savedUser;
        try {
            savedUser = userRepository.save(user);
        }catch (DataIntegrityViolationException ex){
            throw new DataIntegrityViolationException("User with this email already exists");
        }

        return ApiResponse.builder()
                .message("User is created")
                .success(true)
                .data(Map.of("UserId", savedUser.getId()))
                .build();
    }

    @Override
    public ApiResponse<Object> getAllUsers() {
        List<User> users;
        try {
            users =  userRepository.findAll();
        }catch (Exception ex ){
            throw new InternalException("Internal Server Error");
        }
        if(users.isEmpty()){
            return ApiResponse.builder()
                    .success(true)
                    .message("No user Found")
                    .build();
        }

        return ApiResponse.builder()
                .data(users)
                .success(true)
                .message("All users")
                .build();
    }

    @Override
    public ApiResponse<Object> findUserById(String id) {
        User user =  userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return ApiResponse.builder()
                .data(user)
                .success(true)
                .message("User with id" + user.getId())
                .build();

    }


}
