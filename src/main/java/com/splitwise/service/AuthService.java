package com.splitwise.service;

import com.splitwise.dto.request.LoginRequestDTO;
import com.splitwise.dto.request.RegisterRequestDTO;
import com.splitwise.dto.response.ApiResponse;

public interface AuthService {
    ApiResponse<Object> login(LoginRequestDTO loginRequestDTO);
    ApiResponse<Object> register(RegisterRequestDTO registerRequestDTO);
    ApiResponse<Object> verifyEmail(String verificationCode);

}
