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
                .typeOfReviewedItem("game")
                .time(localDateTime)
                .text("text")
                .userId(4)
                .reviewedItemId(4)
                .build();
        Review review2 = Review.builder()
                .id(1L)
                .typeOfReviewedItem("game")
                .time(localDateTime)
                .text("text")
                .userId(4)
                .reviewedItemId(4)
                .build();
        assertEquals(review1, review2);
    }

    @Test
    void compareNotEquals() {
        Review review1 = Review.builder()
                .id(1L)
                .typeOfReviewedItem("game")
                .time(LocalDateTime.now())
                .text("text")
                .userId(4)
                .reviewedItemId(4)
                .build();
        Review review2 = Review.builder()
                .id(2L)
                .typeOfReviewedItem("game")
                .time(LocalDateTime.now())
                .text("text")
                .userId(4)
                .reviewedItemId(4)
                .build();
        assertNotEquals(review1, review2);
    }
}