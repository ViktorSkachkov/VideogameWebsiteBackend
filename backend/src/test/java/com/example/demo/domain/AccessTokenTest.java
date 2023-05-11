package com.example.demo.domain;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AccessTokenTest {
    @Test
    void compareEquals() {
        AccessToken accessToken1 = AccessToken.builder()
                .userId(4L)
                .roles(List.of("role1"))
                .subject("subject")
                .build();
        AccessToken accessToken2 = AccessToken.builder()
                .userId(4L)
                .roles(List.of("role1"))
                .subject("subject")
                .build();
        assertEquals(accessToken1, accessToken2);
    }
    @Test
    void compareNotEquals() {
        AccessToken accessToken1 = AccessToken.builder()
                .userId(4L)
                .roles(List.of("role1"))
                .subject("subject")
                .build();
        AccessToken accessToken2 = AccessToken.builder()
                .userId(4L)
                .roles(List.of("role2"))
                .subject("subject")
                .build();
        assertNotEquals(accessToken1, accessToken2);
    }
}