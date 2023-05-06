package com.example.demo.business.impl.review;

import com.example.demo.business.cases.review.AddReviewUseCase;
import com.example.demo.domain.Review;
import com.example.demo.persistence.domain.persistenceClass.ReviewPersistence;
import com.example.demo.persistence.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddReviewUseCaseImpl implements AddReviewUseCase {
    private final ReviewRepository reviewRepository;

    /**
     *
     * @param review
     * @return
     */
    @Override
    public Review AddReview(Review review) {
        if(review.getText().length() <= 500) {
            ReviewPersistence rp = ReviewPersistence.builder()
                    .text(review.getText())
                    .reviewed_item_id(review.getReviewed_item_id())
                    .time(review.getTime())
                    .user_id(review.getUser_id())
                    .type_of_reviewed_item(review.getType_of_reviewed_item())
                    .build();
            reviewRepository.save(rp);
        }
        else {

        }
        return review;
    }
}
