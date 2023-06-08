package com.example.demo.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginRequestTest {
    @Test
    void compareEquals() {
        LoginRequest loginRequest1 = LoginRequest.builder()
                .username("username")
                .password("password")
                .build();
        LoginRequest loginRequest2 = LoginRequest.builder()
                .username("username")
                .password("password")
                .build();
        assertEquals(loginRequest1, loginRequest2);
    }

    @Test
    void compareNotEquals() {
        LoginRequest loginRequest1 = LoginRequest.builder()
                .username("username")
                .password("password")
                .build();
        LoginRequest loginRequest2 = LoginRequest.builder()
                .username("username1")
                .password("password")
                .build();
        assertNotEquals(loginRequest1, loginRequest2);
    }
}