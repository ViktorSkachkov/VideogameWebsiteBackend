package com.example.demo.business.impl.videogame;

import com.example.demo.persistence.repository.VideogameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidateVideogameNameUseCaseImplTest {
    @Mock
    private VideogameRepository videogameRepository;
    @InjectMocks
    private ValidateVideogameNameUseCaseImpl validateVideogameNameUseCase;

    @Test
    void validateVideogameName() {
        when(videogameRepository.findAllNames(false))
                .thenReturn(List.of("Name"));
        boolean actualResult = validateVideogameNameUseCase.validateVideogameName("Name");
        assertEquals(true, actualResult);
    }
}