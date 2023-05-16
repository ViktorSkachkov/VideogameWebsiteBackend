package com.example.demo.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ReviewTest {
    @Test
    void compareEquals() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Review review1 = Review.builder()
                .id(1L)
                .type_of_reviewed_item("game")
                .time(localDateTime)
                .text("text")
                .user_id(4)
                .reviewed_item_id(4)
                .build();
        Review review2 = Review.builder()
                .id(1L)
                .type_of_reviewed_item("game")
                .time(localDateTime)
                .text("text")
                .user_id(4)
                .reviewed_item_id(4)
                .build();
        assertEquals(review1, review2);
    }

    @Test
    void compareNotEquals() {
        Review review1 = Review.builder()
                .id(1L)
                .type_of_reviewed_item("game")
                .time(LocalDateTime.now())
                .text("text")
                .user_id(4)
                .reviewed_item_id(4)
                .build();
        Review review2 = Review.builder()
                .id(2L)
                .type_of_reviewed_item("game")
                .time(LocalDateTime.now())
                .text("text")
                .user_id(4)
                .reviewed_item_id(4)
                .build();
        assertNotEquals(review1, review2);
    }
}