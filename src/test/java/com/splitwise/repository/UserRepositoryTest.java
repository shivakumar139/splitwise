package com.splitwise.repository;

import com.splitwise.entity.Roles;
import com.splitwise.entity.User;
import com.splitwise.enums.RoleEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void userShouldBeSaved(){

        RoleEnum roleEnum = RoleEnum.ROLE_GROUP_ADMIN;
        roleEnum.setObjectId("shiva");
        Set<Roles> roles = roleEnum.getRolesSet().stream().map(r -> Roles.builder().role(r).build()).collect(Collectors.toSet());

        User user = User.builder()
                .name("jyoti")
                .email("jyoti@gmail.com")
                .password("shiva12345")
                .mobile("9023236998")
                .roles(roles)
                .build();



        assertEquals(userRepository.save(user).getName(), "jyoti");

    }

    @Test
    void printUerRoles(){
        User user = Optional.ofNullable(userRepository.findByEmail("jyoti@gmail.com")).orElseThrow().get();
        log.info(user.getRoles().toString());
    }
}