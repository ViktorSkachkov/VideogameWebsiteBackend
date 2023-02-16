package com.example.demo.business.impl.videogames;

import com.example.demo.domain.Videogame;
import com.example.demo.persistence.repositories.VideogameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddVideogameUseCaseImplTest {
    @Mock
    private VideogameRepository videogameRepository;
    @InjectMocks
    private AddVideogameUseCaseImpl addVideogameUseCase;

    @Test
    void AddVideogame() {
        Videogame expectedResult = Videogame.builder()
                .id(3)
                .name("name3")
                .price(15)
                .description("description3")
                .image("image3")
                .build();
        when(videogameRepository.AddVideogame(expectedResult))
                .thenReturn(expectedResult);
        Videogame actualResult = addVideogameUseCase.AddVideogame(expectedResult);
        assertEquals(expectedResult, actualResult);
        verify(videogameRepository).AddVideogame(expectedResult);
    }
}