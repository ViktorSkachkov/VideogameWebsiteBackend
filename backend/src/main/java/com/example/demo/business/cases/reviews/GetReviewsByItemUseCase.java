package com.example.demo.business.cases.reviews;

import com.example.demo.domain.Review;
import com.example.demo.domain.ReviewRequest;

import java.util.List;

public interface GetReviewsByItemUseCase {
    //List<Review> GetReviewsByItem(ReviewRequest reviewRequest);
    List<Review> GetReviewsByItem(int itemId, String type);
}
