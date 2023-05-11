package com.example.demo.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class UserTest {
    @Test
    void compareEquals() {
        User user1 = User.builder()
                .id(5)
                .username("username5")
                .email("email5")
                .bankAccount("bankAccount5")
                .build();
        User user2 = User.builder()
                .id(5)
                .username("username5")
                .email("email5")
                .bankAccount("bankAccount5")
                .build();
        assertEquals(user1, user2);
    }
    @Test
    void compareNotEquals() {
        User user1 = User.builder()
                .id(5)
                .username("username5")
                .email("email5")
                .bankAccount("bankAccount5")
                .build();
        User user2 = User.builder()
                .id(6)
                .username("username6")
                .email("email5")
                .bankAccount("bankAccount5")
                .build();
        assertNotEquals(user1, user2);
    }
}