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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteReviewUseCaseImplTest {
    @Mock
    private ReviewRepository reviewRepository;
    @InjectMocks
    private DeleteReviewUseCaseImpl deleteReviewUseCase;

    @Test
    void deleteReviewUseCase() {
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
                .id(1L)
                .text("text")
                .reviewedItemId(24)
                .userId(41)
                .time(date)
                .typeOfReviewedItem("game")
                .build();
        when(reviewRepository.findById(1L))
                .thenReturn(Optional.ofNullable(review));
        Review actualResult = deleteReviewUseCase.deleteReview(1);
        assertEquals(expectedResult, actualResult);
        verify(reviewRepository).deleteById(Long.valueOf(1));
    }
}