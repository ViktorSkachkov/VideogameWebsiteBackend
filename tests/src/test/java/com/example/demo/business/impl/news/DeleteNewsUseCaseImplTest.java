package com.example.demo.business.impl.news;

import com.example.demo.domain.News;
import com.example.demo.persistence.entity.NewsPersistence;
import com.example.demo.persistence.repository.NewsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteNewsUseCaseImplTest {
    @Mock
    private NewsRepository newsRepository;
    @InjectMocks
    private DeleteNewsUseCaseImpl deleteNewsUseCase;

    @Test
    void deleteNews() {
        News expectedResult = News.builder()
                .id(1)
                .image("name3")
                .title("title3")
                .text("text3")
                .gameId(1)
                .build();
        NewsPersistence news = NewsPersistence.builder()
                .id(1L)
                .image("name3")
                .title("title3")
                .text("text3")
                .gameId(1)
                .build();
        when(newsRepository.findById(1L))
                .thenReturn(Optional.ofNullable(news));
        News actualResult = deleteNewsUseCase.deleteNews(1);
        assertEquals(expectedResult, actualResult);
        verify(newsRepository).deleteById(Long.valueOf(1));
    }
}