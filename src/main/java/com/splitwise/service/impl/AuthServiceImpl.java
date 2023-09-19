package com.splitwise.service.impl;

import com.splitwise.dto.request.LoginRequestDTO;
import com.splitwise.dto.request.RegisterRequestDTO;
import com.splitwise.dto.response.ApiResponse;
import com.splitwise.entity.User;
import com.splitwise.mapper.CustomMapper;
import com.splitwise.service.AuthService;
import com.splitwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomMapper customMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ApiResponse<Object> login(LoginRequestDTO loginRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ApiResponse.builder()
                .message("User is logged in")
                .success(true)
                .build();
    }

    @Override
    public ApiResponse<Object> register(RegisterRequestDTO registerRequestDTO) {
        User user = customMapper.map(registerRequestDTO);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userService.createUser(user);

        return ApiResponse.builder()
                .message("User is registered")
                .success(true)
                .data(Map.of("UserId", savedUser.getId()))
                .build();
    }
}
