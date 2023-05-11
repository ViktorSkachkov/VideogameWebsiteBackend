package com.example.demo.business.impl.review;

import com.example.demo.domain.News;
import com.example.demo.domain.Review;
import com.example.demo.persistence.domain.persistenceClass.NewsPersistence;
import com.example.demo.persistence.domain.persistenceClass.ReviewPersistence;
import com.example.demo.persistence.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Date;

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
                .reviewed_item_id(24)
                .user_id(41)
                .time(date)
                .type_of_reviewed_item("game")
                .build();
        ReviewPersistence review = ReviewPersistence.builder()
                .text("text")
                .reviewed_item_id(24)
                .user_id(41)
                .time(date)
                .type_of_reviewed_item("game")
                .build();
        when(reviewRepository.save(review))
                .thenReturn(review);
        Review actualResult = addReviewUseCase.addReview(expectedResult);
        assertEquals(expectedResult, actualResult);
        verify(reviewRepository).save(review);
    }
}