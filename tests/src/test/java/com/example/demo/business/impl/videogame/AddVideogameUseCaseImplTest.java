package com.example.demo.business.impl.videogame;

import com.example.demo.domain.Videogame;
import com.example.demo.persistence.entity.VideogamePersistence;
import com.example.demo.persistence.repository.VideogameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddVideogameUseCaseImplTest {
    @Mock
    private VideogameRepository videogameRepository;
    @InjectMocks
    private AddVideogameUseCaseImpl addVideogameUseCase;

    @Test
    void addVideogame() {
        Videogame expectedResult = Videogame.builder()
                .id(5)
                .name("name5")
                .price(15)
                .description("description5")
                .image("image5")
                .deleted(false)
                .build();
        VideogamePersistence videogame = VideogamePersistence.builder()
                .name("name5")
                .price(15)
                .description("description5")
                .image("image5")
                .deleted(false)
                .build();

        when(videogameRepository.save(videogame))
                .thenReturn(videogame);
        Videogame actualResult = addVideogameUseCase.addVideogame(expectedResult);
        assertEquals(expectedResult, actualResult);
        verify(videogameRepository).save(videogame);
    }
}