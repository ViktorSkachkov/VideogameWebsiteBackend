package com.example.demo.business.cases.review;

import com.example.demo.domain.Review;

public interface DeleteReviewUseCase {
    Review deleteReview(int index);
}
