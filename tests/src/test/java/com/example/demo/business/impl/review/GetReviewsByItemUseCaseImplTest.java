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
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetReviewsByItemUseCaseImplTest {
    @Mock
    private ReviewRepository reviewRepository;
    @InjectMocks
    private GetReviewsByItemUseCaseImpl getReviewsByItemUseCase;

    @Test
    void getReviewsByItem() {
        LocalDateTime date = LocalDateTime.now();
        Review review = Review.builder()
                .id(1L)
                .text("text")
                .reviewedItemId(24)
                .typeOfReviewedItem("game")
                .userId(41)
                .time(date)
                .build();
        ReviewPersistence reviewPersistence1 = ReviewPersistence.builder()
                .id(1L)
                .text("text")
                .reviewedItemId(24)
                .typeOfReviewedItem("game")
                .userId(41)
                .time(date)
                .build();
        ReviewPersistence reviewPersistence2 = ReviewPersistence.builder()
                .id(2L)
                .text("text2")
                .reviewedItemId(25)
                .userId(41)
                .time(date)
                .build();

        when(reviewRepository.findAll())
                .thenReturn(List.of(reviewPersistence1, reviewPersistence2));
        List<Review> actualResult = getReviewsByItemUseCase.getReviewsByItem(24, "game");
        List<Review> expectedResult = new ArrayList<>();
        expectedResult.add(review);
        assertEquals(expectedResult, actualResult);
        verify(reviewRepository).findAll();
    }
}