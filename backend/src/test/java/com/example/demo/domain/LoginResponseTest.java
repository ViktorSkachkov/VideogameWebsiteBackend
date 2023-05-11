package com.example.demo.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginResponseTest {
    @Test
    void compareEquals() {
        LoginResponse loginResponse1 = LoginResponse.builder()
                .accessToken("someToken")
                .build();
        LoginResponse loginResponse2 = LoginResponse.builder()
                .accessToken("someToken")
                .build();
        assertEquals(loginResponse1, loginResponse2);
    }
    @Test
    void compareNotEquals() {
        LoginResponse loginResponse1 = LoginResponse.builder()
                .accessToken("someToken")
                .build();
        LoginResponse loginResponse2 = LoginResponse.builder()
                .accessToken("someToken1")
                .build();
        assertNotEquals(loginResponse1, loginResponse2);
    }
}