package com.example.demo.business.impl.videogame;

import com.example.demo.domain.Videogame;
import com.example.demo.persistence.entity.AdditionPersistence;
import com.example.demo.persistence.entity.NewsPersistence;
import com.example.demo.persistence.entity.VideogamePersistence;
import com.example.demo.persistence.repository.NewsRepository;
import com.example.demo.persistence.repository.VideogameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetVideogamesForNewsFilterUseCaseImplTest {
    @Mock
    private VideogameRepository videogameRepository;
    @Mock
    private NewsRepository newsRepository;
    @InjectMocks
    private GetVideogamesForNewsFilterUseCaseImpl getVideogamesForNewsFilterUseCaseImpl;

    @Test
    void GetVideogamesForAdditionsFilter() {
        Videogame videogame2 = Videogame.builder()
                .id(1)
                .name("name1")
                .price(10)
                .featured(true)
                .description("description1")
                .image("image1")
                .build();
        VideogamePersistence videogamePersistence1 = VideogamePersistence.builder()
                .id(1L)
                .name("name1")
                .price(10)
                .featured(true)
                .description("description1")
                .image("image1")
                .build();
        VideogamePersistence videogamePersistence2 = VideogamePersistence.builder()
                .id(2L)
                .name("name2")
                .price(10)
                .featured(true)
                .description("description2")
                .image("image2")
                .build();

        NewsPersistence newsPersistence1 = NewsPersistence.builder()
                .id(2L)
                .image("name2")
                .title("title2")
                .text("text2")
                .game_id(1)
                .build();

        when(newsRepository.findAll())
                .thenReturn(List.of(newsPersistence1));

        when(videogameRepository.findAll())
                .thenReturn(List.of(videogamePersistence1, videogamePersistence2));
        List<Videogame> actualResult = getVideogamesForNewsFilterUseCaseImpl.getVideogamesorNewsFilter();
        List<Videogame> expectedResult = new ArrayList<>();
        expectedResult.add(videogame2);
        assertEquals(expectedResult, actualResult);
        verify(videogameRepository).findAll();
    }
}