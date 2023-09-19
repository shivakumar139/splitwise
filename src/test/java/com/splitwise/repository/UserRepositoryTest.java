package com.splitwise.repository;

import com.splitwise.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void userShouldBeSaved(){
        User user = User.builder()
                .name("shiva")
                .email("shiva@gmail.com")
                .password("shiva12345")
                .mobile("9023236998")
                .build();

        assertEquals(userRepository.save(user).getName(), "shiva");

    }
}