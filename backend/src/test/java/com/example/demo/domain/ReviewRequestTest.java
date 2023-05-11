package com.example.demo.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ReviewRequestTest {
    @Test
    void compareEquals() {
        ReviewRequest reviewRequest1 = ReviewRequest.builder()
                .id(1)
                .type_of_reviewed_item("game")
                .reviewed_item_id(4)
                .build();
        ReviewRequest reviewRequest2 = ReviewRequest.builder()
                .id(1)
                .type_of_reviewed_item("game")
                .reviewed_item_id(4)
                .build();
        assertEquals(reviewRequest1, reviewRequest2);
    }
    @Test
    void compareNotEquals() {
        ReviewRequest reviewRequest1 = ReviewRequest.builder()
                .id(1)
                .type_of_reviewed_item("game")
                .reviewed_item_id(4)
                .build();
        ReviewRequest reviewRequest2 = ReviewRequest.builder()
                .id(2)
                .type_of_reviewed_item("game")
                .reviewed_item_id(4)
                .build();
        assertNotEquals(reviewRequest1, reviewRequest2);
    }
}