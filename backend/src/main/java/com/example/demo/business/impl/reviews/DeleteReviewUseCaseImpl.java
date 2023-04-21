package com.example.demo.business.impl.reviews;

import com.example.demo.business.cases.reviews.DeleteReviewUseCase;
import com.example.demo.domain.Review;
import com.example.demo.domain.persistenceClasses.ReviewPersistence;
import com.example.demo.persistence.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeleteReviewUseCaseImpl implements DeleteReviewUseCase {
    private final ReviewRepository reviewRepository;

    @Override
    public Review DeleteReview(int index) {
        Optional<ReviewPersistence> rp = reviewRepository.findById(Long.valueOf(index));
        if(rp.isEmpty()) {

        }
        reviewRepository.deleteById(Long.valueOf(index));
        Review review = Review.builder()
                .id((long) Math.toIntExact(rp.get().getId()))
                .text(rp.get().getText())
                .reviewed_item_id(rp.get().getReviewed_item_id())
                .time(rp.get().getTime())
                .user_id(rp.get().getUser_id())
                .type_of_reviewed_item(rp.get().getType_of_reviewed_item())
                .build();
        return review;
    }
}
