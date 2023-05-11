package com.example.demo.business.cases.review;

import com.example.demo.domain.Review;

import java.util.List;

public interface GetReviewsByItemUseCase {
    List<Review> getReviewsByItem(int itemId, String type);
}
