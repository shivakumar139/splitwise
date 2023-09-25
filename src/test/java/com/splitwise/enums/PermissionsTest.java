package com.splitwise.enums;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class PermissionsTest {


    @Test
    void withObjectId() {
        log.info(String.valueOf(Permissions.GROUP_ADD_EXPENSE));
        log.info(Permissions.GROUP_ADD_EXPENSE.withObjectId("shiva"));
    }
}