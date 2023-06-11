package com.example.demo.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationResponseTest {
    @Test
    void compareEquals() {
        ValidationResponse validationResponse1 = ValidationResponse.builder()
                .confirm(true)
                .build();
        ValidationResponse validationResponse2 = ValidationResponse.builder()
                .confirm(true)
                .build();
        assertEquals(validationResponse1, validationResponse2);
    }

    @Test
    void compareNotEquals() {
        ValidationResponse validationResponse1 = ValidationResponse.builder()
                .confirm(true)
                .build();
        ValidationResponse validationResponse2 = ValidationResponse.builder()
                .confirm(false)
                .build();
        assertNotEquals(validationResponse1, validationResponse2);
    }
}