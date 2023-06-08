package com.example.demo.business.impl.review;

import com.example.demo.domain.Review;
import com.example.demo.persistence.entity.ReviewPersistence;
import com.example.demo.persistence.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddReviewUseCaseImplTest {
    @Mock
    private ReviewRepository reviewRepository;
    @InjectMocks
    private AddReviewUseCaseImpl addReviewUseCase;

    @Test
    void addReview() {
        LocalDateTime date = LocalDateTime.now();
        Review expectedResult = Review.builder()
                .id(1L)
                .text("text")
                .reviewedItemId(24)
                .userId(41)
                .time(date)
                .typeOfReviewedItem("game")
                .build();
        ReviewPersistence review = ReviewPersistence.builder()
                .text("text")
                .reviewedItemId(24)
                .userId(41)
                .time(date)
                .typeOfReviewedItem("game")
                .build();
        when(reviewRepository.save(review))
                .thenReturn(review);
        Review actualResult = addReviewUseCase.addReview(expectedResult);
        assertEquals(expectedResult, actualResult);
        verify(reviewRepository).save(review);
    }
}