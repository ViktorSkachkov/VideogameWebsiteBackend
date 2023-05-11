package com.example.demo.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameOrderTest {
    @Test
    void compareEquals() {
        GameOrder gameOrder1 = GameOrder.builder()
                .id(1)
                .units(2)
                .game(4)
                .user(4)
                .build();
        GameOrder gameOrder2 = GameOrder.builder()
                .id(1)
                .units(2)
                .game(4)
                .user(4)
                .build();
        assertEquals(gameOrder1, gameOrder2);
    }
    @Test
    void compareNotEquals() {
        GameOrder gameOrder1 = GameOrder.builder()
                .id(1)
                .units(2)
                .game(4)
                .user(4)
                .build();
        GameOrder gameOrder2 = GameOrder.builder()
                .id(2)
                .units(2)
                .game(4)
                .user(4)
                .build();
        assertNotEquals(gameOrder1, gameOrder2);
    }
}