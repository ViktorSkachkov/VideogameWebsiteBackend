package com.example.demo.business.impl.additions;

import com.example.demo.business.cases.additions.AddAdditionUseCase;
import com.example.demo.domain.Addition;
import com.example.demo.persistence.repositories.AdditionRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddAdditionUseCaseImplTest {
    @Mock
    private AdditionRepository additionRepository;
    @InjectMocks
    private AddAdditionUseCaseImpl addAdditionUseCase;

    @Test
    void AddAddition() {
        Addition expectedResult = Addition.builder()
                .id(3)
                .gameId(1)
                .name("name3")
                .price(10)
                .description("description3")
                .image("image3")
                .build();
        when(additionRepository.AddAddition(expectedResult))
                .thenReturn(expectedResult);
        Addition actualResult = addAdditionUseCase.AddAddition(expectedResult);
        assertEquals(expectedResult, actualResult);
        verify(additionRepository).AddAddition(expectedResult);
    }
}