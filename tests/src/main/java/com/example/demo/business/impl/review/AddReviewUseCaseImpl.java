package com.example.demo.business.impl.review;

import com.example.demo.business.cases.review.AddReviewUseCase;
import com.example.demo.domain.Review;
import com.example.demo.persistence.entity.ReviewPersistence;
import com.example.demo.persistence.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddReviewUseCaseImpl implements AddReviewUseCase {
    private final ReviewRepository reviewRepository;

    /**
     * @param review
     * @return
     */
    @Override
    public Review addReview(Review review) {
        if (review.getText().length() <= 500) {
            ReviewPersistence rp = ReviewPersistence.builder()
                    .text(review.getText())
                    .reviewedItemId(review.getReviewedItemId())
                    .time(review.getTime())
                    .userId(review.getUserId())
                    .typeOfReviewedItem(review.getTypeOfReviewedItem())
                    .build();
            reviewRepository.save(rp);
        } else {

        }
        return review;
    }
}
