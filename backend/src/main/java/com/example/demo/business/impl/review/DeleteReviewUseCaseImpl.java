package com.example.demo.business.impl.review;

import com.example.demo.business.cases.review.DeleteReviewUseCase;
import com.example.demo.domain.Review;
import com.example.demo.persistence.entity.ReviewPersistence;
import com.example.demo.persistence.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeleteReviewUseCaseImpl implements DeleteReviewUseCase {
    private final ReviewRepository reviewRepository;

    /**
     * @param index
     * @return
     */
    @Override
    public Review deleteReview(int index) {
        Optional<ReviewPersistence> rp = reviewRepository.findById(Long.valueOf(index));
        if (rp.isEmpty()) {

        }
        reviewRepository.deleteById(Long.valueOf(index));
        return Review.builder()
                .id((long) Math.toIntExact(rp.get().getId()))
                .text(rp.get().getText())
                .reviewedItemId(rp.get().getReviewedItemId())
                .time(rp.get().getTime())
                .userId(rp.get().getUserId())
                .typeOfReviewedItem(rp.get().getTypeOfReviewedItem())
                .build();
    }
}
