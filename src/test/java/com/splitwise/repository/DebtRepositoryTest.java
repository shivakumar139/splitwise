package com.splitwise.repository;

import com.splitwise.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DebtRepositoryTest {

    @Autowired
    private DebtRepository debtRepository;
    @Autowired
    private UserRepository userRepository;


    @Test
    void payerAndPayeeBothCombinationShouldExists() {

        User payer = userRepository.findById("8a191692-f99a-464e-8e42-2ac1101ccbea").get(); // parish
        User payee = userRepository.findById("9d8345b6-895b-40bd-bbe6-a4656cfd61e8").get(); // shiva

//        assertNotEquals(debtRepository.findByPayerAndPayee(payer, payee), null);
//        assertNull(debtRepository.findByPayerAndPayee(payee, payer));

//        assertTrue(debtRepository.existsByPayerAndPayee(payer, payee));
        assertTrue(debtRepository.existsByPayerAndPayee(payee, payer));


    }
}