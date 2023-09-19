package com.splitwise.service.impl;

import com.splitwise.entity.User;
import com.splitwise.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    void createUser() {
            User user = User.builder()
                    .name("shiva")
                    .email("shiva@gmail.com")
                    .password("shiva12345")
                    .mobile("9023236998")
                    .build();

            assertEquals(userService.createUser(user).getName(), "shiva");
    }
}