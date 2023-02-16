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
class UpdateVideogameUseCaseImplTest {
    @Mock
    private VideogameRepository videogameRepository;
    @InjectMocks
    private UpdateVideogameUseCaseImpl updateVideogameUseCase;

    @Test
    void UpdateVideogame() {
        Videogame expectedResult = Videogame.builder()
                .id(1)
                .name("name3")
                .price(15)
                .description("description3")
                .image("image3")
                .build();
        when(videogameRepository.UpdateVideogame(expectedResult))
                .thenReturn(expectedResult);
        Videogame actualResult = updateVideogameUseCase.UpdateVideogame(expectedResult);
        assertEquals(expectedResult, actualResult);
        verify(videogameRepository).UpdateVideogame(expectedResult);
    }
}