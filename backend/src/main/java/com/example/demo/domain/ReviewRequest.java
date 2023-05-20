package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ReviewRequest {
    private int id;
    private int reviewedItemId;
    private String typeOfReviewedItem;
}
