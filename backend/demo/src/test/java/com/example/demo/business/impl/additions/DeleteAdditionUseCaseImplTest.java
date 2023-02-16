package com.example.demo.business.impl.additions;

import com.example.demo.domain.Addition;
import com.example.demo.persistence.repositories.AdditionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteAdditionUseCaseImplTest {
    @Mock
    private AdditionRepository additionRepository;
    @InjectMocks
    private DeleteAdditionUseCaseImpl deleteAdditionUseCase;

    @Test
    void DeleteAddition() {
        Addition expectedResult = Addition.builder()
                .id(1)
                .gameId(1)
                .name("name1")
                .price(10)
                .description("description1")
                .image("image1")
                .build();
        when(additionRepository.DeleteAddition(1))
                .thenReturn(expectedResult);
        Addition actualResult = additionRepository.DeleteAddition(1);
        assertEquals(expectedResult, actualResult);
        verify(additionRepository).DeleteAddition(1);
    }
}