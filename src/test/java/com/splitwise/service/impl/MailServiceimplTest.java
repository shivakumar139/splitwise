package com.splitwise.service.impl;

import com.splitwise.entity.User;
import com.splitwise.repository.UserRepository;
import com.splitwise.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MailServiceimplTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MailService mailService;

    @Test
    void sendMail() {
        User user = userRepository.findById("e2c8657d-bb23-4aaa-9cb1-9abdd21a93a8").get();

        mailService.sendMail(user);
    }
}