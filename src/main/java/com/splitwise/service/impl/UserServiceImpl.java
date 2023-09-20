package com.splitwise.service.impl;

import com.splitwise.dto.response.ApiResponse;
import com.splitwise.entity.User;
import com.splitwise.exception.UserNotFoundException;
import com.splitwise.mapper.CustomMapper;
import com.splitwise.repository.UserRepository;
import com.splitwise.service.UserService;
import com.sun.jdi.InternalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private CustomMapper customMapper;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
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
                .data(customMapper.mapToUserDto(users))
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

    @Override
    public UserDetails findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User findByVerificationCode(String verificationCode) {
       return userRepository.findByVerificationCode(verificationCode).orElseThrow(UserNotFoundException::new);

    }


}
