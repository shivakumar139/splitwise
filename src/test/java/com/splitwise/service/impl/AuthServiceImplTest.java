package com.splitwise.service.impl;

import com.splitwise.dto.request.RegisterRequestDTO;
import com.splitwise.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthServiceImplTest {

    @Autowired
    private AuthService authService;

    @Test
    void register() {
        RegisterRequestDTO registerRequestDTO = RegisterRequestDTO.builder()
                .name("shiva")
                .email("shiva@gmail.com")
                .mobile("9023236998")
                .password("shiva1344545")
                .build();

        assertTrue(authService.register(registerRequestDTO).isSuccess());
    }
}