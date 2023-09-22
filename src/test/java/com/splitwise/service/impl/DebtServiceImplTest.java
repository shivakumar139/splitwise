package com.splitwise.service.impl;

import com.splitwise.entity.User;
import com.splitwise.repository.UserRepository;
import com.splitwise.service.DebtService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class DebtServiceImplTest {

    @Autowired
    DebtService debtService;

    @Autowired
    UserRepository userRepository;

    @Test
    void PayeeAndPayerShouldExists() {
        User payer = userRepository.findById("8a191692-f99a-464e-8e42-2ac1101ccbea").orElseThrow(); // parish
        User payee = userRepository.findById("9d8345b6-895b-40bd-bbe6-a4656cfd61e8")
                .orElseThrow(() -> {
                    log.error("User is not present against usier id:{} ","9d8345b6-895b-40bd-bbe6-a4656cfd61e8");
                    return new RuntimeException("User is not present");
                }); // shiva

        debtService.getDebtById("");
    }
}