package com.example.demo.business.cases.reviews;

import com.example.demo.domain.Review;

public interface DeleteReviewUseCase {
    Review DeleteReview(int index);
}
