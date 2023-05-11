package com.example.demo.business.impl.review;

import com.example.demo.business.cases.review.UpdateReviewUseCase;
import com.example.demo.domain.Review;
import com.example.demo.persistence.domain.persistenceClass.ReviewPersistence;
import com.example.demo.persistence.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateReviewUseCaseImpl implements UpdateReviewUseCase {
    private final ReviewRepository reviewRepository;

    /**
     *
     * @param review
     * @return
     */
    @Override
    public Review updateReview(Review review) {
        Optional<ReviewPersistence> rp = reviewRepository.findById(Long.valueOf(review.getId()));
        if(rp.isEmpty()) {

        }

        rp.get().setText(review.getText());
        rp.get().setReviewed_item_id(review.getReviewed_item_id());
        rp.get().setType_of_reviewed_item(review.getType_of_reviewed_item());
        rp.get().setUser_id(review.getUser_id());
        rp.get().setTime(review.getTime());
        reviewRepository.save(rp.get());
        return review;
    }
}
