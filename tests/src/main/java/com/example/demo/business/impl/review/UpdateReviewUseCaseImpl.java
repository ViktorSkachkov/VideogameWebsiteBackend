package com.example.demo.business.impl.review;

import com.example.demo.business.cases.review.UpdateReviewUseCase;
import com.example.demo.domain.Review;
import com.example.demo.exception.IsEmptyException;
import com.example.demo.persistence.entity.ReviewPersistence;
import com.example.demo.persistence.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateReviewUseCaseImpl implements UpdateReviewUseCase {
    private final ReviewRepository reviewRepository;

    /**
     * @param review
     * @return
     */
    @Override
    public Review updateReview(Review review) {
        Optional<ReviewPersistence> rp = reviewRepository.findById(Long.valueOf(review.getId()));
        if (rp.isEmpty()) {
            throw new IsEmptyException();
        }

        rp.get().setText(review.getText());
        rp.get().setReviewedItemId(review.getReviewedItemId());
        rp.get().setTypeOfReviewedItem(review.getTypeOfReviewedItem());
        rp.get().setUserId(review.getUserId());
        rp.get().setTime(review.getTime());
        reviewRepository.save(rp.get());
        return review;
    }
}
