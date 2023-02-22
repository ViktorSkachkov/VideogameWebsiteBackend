package com.example.demo.business.impl.videogames;

import com.example.demo.domain.Videogame;
import com.example.demo.persistence.repositories.VideogameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class GetVideogameUseCaseImplTest {
    @Mock
    private VideogameRepository videogameRepository;
    @InjectMocks
    private GetVideogameUseCaseImpl getVideogameUseCase;

    @Test
    void GetVideogame() throws Exception  {
        Videogame expectedResult = Videogame.builder()
                .id(1)
                .name("name1")
                .price(10)
                .description("description1")
                .image("image1")
                .build();
        when(videogameRepository.GetVideogame(1))
                .thenReturn(expectedResult);
        Videogame actualResult = getVideogameUseCase.GetVideogame(1);
        assertEquals(expectedResult, actualResult);
        verify(videogameRepository).GetVideogame(1);
    }
}