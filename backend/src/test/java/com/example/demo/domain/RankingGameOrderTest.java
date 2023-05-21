package com.example.demo.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RankingGameOrderTest {
    @Test
    void compareEquals() {
        RankingGameOrder rankingGameOrder1 = RankingGameOrder.builder()
                .id(1L)
                .totalPrice(48)
                .numberOfTimesBought(4)
                .gameOrderList(List.of(GameOrder.builder()
                        .id(1)
                        .units(2)
                        .game(4)
                        .user(4)
                        .build()))
                .name("Name")
                .price(12)
                .build();

        RankingGameOrder rankingGameOrder2 = RankingGameOrder.builder()
                .id(1L)
                .totalPrice(48)
                .numberOfTimesBought(4)
                .gameOrderList(List.of(GameOrder.builder()
                        .id(1)
                        .units(2)
                        .game(4)
                        .user(4)
                        .build()))
                .name("Name")
                .price(12)
                .build();
        assertEquals(rankingGameOrder1, rankingGameOrder2);
    }

    @Test
    void compareNotEquals() {
        RankingGameOrder rankingGameOrder1 = RankingGameOrder.builder()
                .id(1L)
                .totalPrice(48)
                .numberOfTimesBought(4)
                .gameOrderList(List.of(GameOrder.builder()
                        .id(1)
                        .units(2)
                        .game(4)
                        .user(4)
                        .build()))
                .name("Name")
                .price(12)
                .build();

        RankingGameOrder rankingGameOrder2 = RankingGameOrder.builder()
                .id(2L)
                .totalPrice(48)
                .numberOfTimesBought(4)
                .gameOrderList(List.of(GameOrder.builder()
                        .id(1)
                        .units(2)
                        .game(4)
                        .user(4)
                        .build()))
                .name("Name")
                .price(12)
                .build();
        assertNotEquals(rankingGameOrder1, rankingGameOrder2);
    }
}