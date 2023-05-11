package com.example.demo.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdditionOrderTest {
    @Test
    void compareEquals() {
        AdditionOrder additionOrder1 = AdditionOrder.builder()
                .id(1)
                .units(2)
                .addition(4)
                .user(4)
                .build();
        AdditionOrder additionOrder2 = AdditionOrder.builder()
                .id(1)
                .units(2)
                .addition(4)
                .user(4)
                .build();
        assertEquals(additionOrder1, additionOrder2);
    }
    @Test
    void compareNotEquals() {
        AdditionOrder additionOrder1 = AdditionOrder.builder()
                .id(1)
                .units(2)
                .addition(4)
                .user(4)
                .build();
        AdditionOrder additionOrder2 = AdditionOrder.builder()
                .id(2)
                .units(2)
                .addition(4)
                .user(4)
                .build();
        assertNotEquals(additionOrder1, additionOrder2);
    }
}