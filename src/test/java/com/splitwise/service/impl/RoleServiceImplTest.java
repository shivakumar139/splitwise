package com.splitwise.service.impl;

import com.splitwise.enums.RoleEnum;
import com.splitwise.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class RoleServiceImplTest {

    @Autowired
    private RoleService roleService;
    @Test
    void createRole() {
        log.info(RoleEnum.ROLE_GROUP_ADMIN.withObjectId("shiva"));
        log.info(roleService.createRole(RoleEnum.ROLE_GROUP_ADMIN, "shiva").toString());
    }
}