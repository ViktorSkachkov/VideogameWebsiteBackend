package com.example.demo.business.impl.additions;

import com.example.demo.domain.Addition;
import com.example.demo.domain.persistenceClasses.AdditionPersistence;
import com.example.demo.persistence.repositories.AdditionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateAdditionUseCaseImplTest {
    @Mock
    private AdditionRepository additionRepository;
    @InjectMocks
    private UpdateAdditionUseCaseImpl updateAdditionUseCase;

    @Test
    void UpdateAddition() throws Exception  {
        Addition expectedResult = Addition.builder()
                .id(1)
                .gameId(2)
                .name("name3")
                .price(15)
                .description("description3")
                .image("image3")
                .build();
        AdditionPersistence addition = AdditionPersistence.builder()
                .id(1L)
                .game_id(2)
                .name("name3")
                .price(15)
                .description("description3")
                .image("image3")
                .build();
        when(additionRepository.save(addition))
                .thenReturn(addition);
        Addition actualResult = updateAdditionUseCase.UpdateAddition(expectedResult);
        assertEquals(expectedResult, actualResult);
        verify(additionRepository).save(addition);
    }
}