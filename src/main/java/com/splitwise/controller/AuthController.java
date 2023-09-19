package com.splitwise.controller;

import com.splitwise.dto.request.LoginRequestDTO;
import com.splitwise.dto.request.RegisterRequestDTO;
import com.splitwise.dto.response.ApiResponse;
import com.splitwise.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("login")
    public ResponseEntity<ApiResponse<Object>> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO){
        return new ResponseEntity<>(authService.login(loginRequestDTO), HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<ApiResponse<Object>> register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO){
        return new ResponseEntity<>(authService.register(registerRequestDTO), HttpStatus.OK);
    }
}
