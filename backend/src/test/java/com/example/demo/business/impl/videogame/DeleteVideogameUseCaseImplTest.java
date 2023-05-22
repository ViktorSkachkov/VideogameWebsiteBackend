package com.example.demo.business.impl.videogame;

import com.example.demo.domain.Review;
import com.example.demo.domain.Videogame;
import com.example.demo.persistence.entity.AdditionPersistence;
import com.example.demo.persistence.entity.NewsPersistence;
import com.example.demo.persistence.entity.ReviewPersistence;
import com.example.demo.persistence.entity.VideogamePersistence;
import com.example.demo.persistence.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteVideogameUseCaseImplTest {
    @Mock
    private VideogameRepository videogameRepository;
    @Mock
    private AdditionRepository additionRepository;
    @Mock
    private NewsRepository newsRepository;
    @Mock
    private ReviewRepository reviewRepository;
    @InjectMocks
    private DeleteVideogameUseCaseImpl deleteVideogameUseCase;

    @Test
    void deleteVideogame() {
        Videogame expectedResult = Videogame.builder()
                .id(1)
                .name("name1")
                .price(10)
                .description("description1")
                .image("image1")
                .deleted(true)
                .build();
        VideogamePersistence videogame = VideogamePersistence.builder()
                .id(1L)
                .name("name1")
                .price(10)
                .description("description1")
                .image("image1")
                .deleted(true)
                .build();
        when(videogameRepository.findById(1L))
                .thenReturn(Optional.ofNullable(videogame));
        Videogame actualResult = deleteVideogameUseCase.deleteVideogame(1);
        assertEquals(expectedResult, actualResult);
        verify(videogameRepository).save(videogame);
    }

    @Test
    void deleteReviews() {
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

        List<ReviewPersistence> reviewList = new ArrayList<>();
        reviewList.add(review);

        List<Integer> ids = new ArrayList<>();
        ids.add(expectedResult.getReviewedItemId());

        List<ReviewPersistence> actualResult = deleteVideogameUseCase.deleteReviews(reviewList, ids, expectedResult.getReviewedItemId());
        assertEquals(reviewList, actualResult);
        verify(reviewRepository).deleteById(Long.valueOf(1));
    }

    @Test
    void deleteAdditions() {
        List<Integer> expectedResult = new ArrayList<>();
        AdditionPersistence addition = AdditionPersistence.builder()
                .id(1L)
                .gameId(1)
                .name("name3")
                .price(10)
                .description("description3")
                .image("image3")
                .deleted(false)
                .build();

        List<AdditionPersistence> additionPersistenceList = new ArrayList<>();
        additionPersistenceList.add(addition);

        expectedResult.add(Math.toIntExact(addition.getId()));

        AdditionPersistence addition2 = AdditionPersistence.builder()
                .id(1L)
                .gameId(1)
                .name("name3")
                .price(10)
                .description("description3")
                .image("image3")
                .deleted(true)
                .build();

        when(additionRepository.save(addition2))
                .thenReturn(addition2);
        List<Integer> actualResult = deleteVideogameUseCase.deleteAdditions(additionPersistenceList, addition.getGameId());

        assertEquals(expectedResult, actualResult);
        verify(additionRepository).save(addition2);
    }

    @Test
    void deleteNews() {
        List<NewsPersistence> expectedResult = new ArrayList<>();
        NewsPersistence news = NewsPersistence.builder()
                .id(1L)
                .gameId(1)
                .text("description3")
                .image("image3")
                .build();

        List<NewsPersistence> newsPersistenceList = new ArrayList<>();
        newsPersistenceList.add(news);

        expectedResult.add(news);

        List<NewsPersistence> actualResult = deleteVideogameUseCase.deleteNews(newsPersistenceList, news.getGameId());

        assertEquals(expectedResult, actualResult);
        verify(newsRepository).deleteById(news.getId());
    }
}