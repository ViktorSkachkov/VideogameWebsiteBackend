package com.example.demo.business.impl.videogame;

import com.example.demo.domain.Videogame;
import com.example.demo.persistence.entity.VideogamePersistence;
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
class GetFeaturedVideogamesUseCaseImplTest {
    @Mock
    private VideogameRepository videogameRepository;
    @InjectMocks
    private GetFeaturedVideogamesUseCaseImpl getFeaturedVideogamesUseCase;

    @Test
    void getFeaturedVideogames() {
        Videogame videogame1 = Videogame.builder()
                .id(1)
                .name("name1")
                .price(10)
                .featured(true)
                .description("description1")
                .image("image1")
                .deleted(false)
                .build();
        VideogamePersistence videogamePersistence1 = VideogamePersistence.builder()
                .id(1L)
                .name("name1")
                .price(10)
                .featured(true)
                .description("description1")
                .image("image1")
                .deleted(false)
                .build();
        VideogamePersistence videogamePersistence2 = VideogamePersistence.builder()
                .id(2L)
                .name("name2")
                .price(10)
                .featured(false)
                .description("description2")
                .image("image2")
                .deleted(false)
                .build();
        when(videogameRepository.findAll())
                .thenReturn(List.of(videogamePersistence1, videogamePersistence2));
        List<Videogame> actualResult = getFeaturedVideogamesUseCase.getFeaturedVideogames();
        List<Videogame> expectedResult = new ArrayList<>();
        expectedResult.add(videogame1);
        assertEquals(expectedResult, actualResult);
        verify(videogameRepository).findAll();
    }
}