package com.example.demo.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RankingAdditionOrderTest {
    @Test
    void compareEquals() {
        RankingAdditionOrder rankingAdditionOrder1 = RankingAdditionOrder.builder()
                .id(1L)
                .totalPrice(48)
                .numberOfTimesBought(4)
                .additionOrderList(List.of(AdditionOrder.builder()
                        .id(1)
                        .units(2)
                        .addition(4)
                        .user(4)
                        .build()))
                .name("Name")
                .price(12)
                .build();

        RankingAdditionOrder rankingAdditionOrder2 = RankingAdditionOrder.builder()
                .id(1L)
                .totalPrice(48)
                .numberOfTimesBought(4)
                .additionOrderList(List.of(AdditionOrder.builder()
                        .id(1)
                        .units(2)
                        .addition(4)
                        .user(4)
                        .build()))
                .name("Name")
                .price(12)
                .build();
        assertEquals(rankingAdditionOrder1, rankingAdditionOrder2);
    }

    @Test
    void compareNotEquals() {
        RankingAdditionOrder rankingAdditionOrder1 = RankingAdditionOrder.builder()
                .id(1L)
                .totalPrice(48)
                .numberOfTimesBought(4)
                .additionOrderList(List.of(AdditionOrder.builder()
                        .id(1)
                        .units(2)
                        .addition(4)
                        .user(4)
                        .build()))
                .name("Name")
                .price(12)
                .build();

        RankingAdditionOrder rankingAdditionOrder2 = RankingAdditionOrder.builder()
                .id(2L)
                .totalPrice(48)
                .numberOfTimesBought(4)
                .additionOrderList(List.of(AdditionOrder.builder()
                        .id(1)
                        .units(2)
                        .addition(4)
                        .user(4)
                        .build()))
                .name("Name")
                .price(12)
                .build();
        assertNotEquals(rankingAdditionOrder1, rankingAdditionOrder2);
    }
}