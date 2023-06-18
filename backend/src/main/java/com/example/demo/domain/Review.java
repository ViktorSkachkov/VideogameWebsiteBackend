package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@Builder
public class Review {
    private Long id;
    private int reviewedItemId;
    private int userId;
    private String text;
    private LocalDateTime time;
    private String typeOfReviewedItem;
}
