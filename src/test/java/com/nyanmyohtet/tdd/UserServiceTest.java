package com.nyanmyohtet.tdd;

import com.nyanmyohtet.tdd.model.User;
import com.nyanmyohtet.tdd.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void shouldCreateUserSuccessfully() {
        User user = new User();
        user.setName("John");
        user.setEmail("john@example.com");
        User savedUser = userService.createUser(user);

        assertNotNull(savedUser.getId());
        assertEquals("John", savedUser.getName());
    }
}
