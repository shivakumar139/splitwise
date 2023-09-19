package com.splitwise.service.impl;

import com.splitwise.entity.User;
import com.splitwise.repository.UserRepository;
import com.splitwise.service.DebtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DebtServiceImplTest {

    @Autowired
    DebtService debtService;

    @Autowired
    UserRepository userRepository;

    @Test
    void PayeeAndPayerShouldExists() {
        User payer = userRepository.findById("8a191692-f99a-464e-8e42-2ac1101ccbea").get(); // parish
        User payee = userRepository.findById("9d8345b6-895b-40bd-bbe6-a4656cfd61e8").get(); // shiva

        assertTrue(debtService.existsByPayerAndPayee(payee, payer));
    }
}