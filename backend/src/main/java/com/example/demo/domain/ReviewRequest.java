package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ReviewRequest {
    private int id;
    private int reviewed_item_id;
    private String type_of_reviewed_item;
}
