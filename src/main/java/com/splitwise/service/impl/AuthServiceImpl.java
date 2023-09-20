package com.splitwise.service.impl;

import com.splitwise.dto.request.LoginRequestDTO;
import com.splitwise.dto.request.RegisterRequestDTO;
import com.splitwise.dto.response.ApiResponse;
import com.splitwise.entity.User;
import com.splitwise.mapper.CustomMapper;
import com.splitwise.service.AuthService;
import com.splitwise.service.JwtService;
import com.splitwise.service.MailService;
import com.splitwise.service.UserService;
import com.sun.jdi.InternalException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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

    @Autowired
    private JwtService jwtService;

    @Autowired
    private MailService mailService;

    @Override
    public ApiResponse<Object> login(LoginRequestDTO loginRequestDTO) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = userService.findByEmail(loginRequestDTO.getEmail());
        String jwtToken = jwtService.createToken(userDetails, new HashMap<>());

        return ApiResponse.builder()
                .message("User is logged in")
                .success(true)
                .data(Map.of("Token", jwtToken))
                .build();
    }

    @Override
    public ApiResponse<Object> register(RegisterRequestDTO registerRequestDTO) {
        User user = customMapper.map(registerRequestDTO);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userService.createUser(user);

        mailService.sendMail(savedUser);


        return ApiResponse.builder()
                .message("User is registered")
                .success(true)
                .data(Map.of("UserId", savedUser.getId()))
                .build();
    }

    @Transactional
    @Override
    public ApiResponse<Object> verifyEmail(String verificationCode) {
        User user = userService.findByVerificationCode(verificationCode);

        user.setVerificationCode(null);
        user.setEnabled(true);

        return ApiResponse.builder()
                .success(true)
                .message("Email is verified")
                .build();
    }


}
