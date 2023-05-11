package com.example.demo.domain;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class RoleTest {
    @Test
    void compareEquals() {
        Role role1 = Role.builder()
                .id(1)
                .role("CUSTOMER")
                .user_id(3)
                .build();
        Role role2 = Role.builder()
                .id(1)
                .role("CUSTOMER")
                .user_id(3)
                .build();
        assertEquals(role1, role2);
    }
    @Test
    void compareNotEquals() {
        Role role1 = Role.builder()
                .id(1)
                .role("CUSTOMER")
                .user_id(3)
                .build();
        Role role2 = Role.builder()
                .id(2)
                .role("CUSTOMER")
                .user_id(3)
                .build();
        assertNotEquals(role1, role2);
    }
}