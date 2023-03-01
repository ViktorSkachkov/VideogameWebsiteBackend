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
class DeleteAdditionUseCaseImplTest {
    @Mock
    private AdditionRepository additionRepository;
    @InjectMocks
    private DeleteAdditionUseCaseImpl deleteAdditionUseCase;

    @Test
    void DeleteAddition() throws Exception  {
        Addition addition = Addition.builder()
                .id(1)
                .gameId(1)
                .name("name1")
                .price(10)
                .description("description1")
                .image("image1")
                .build();
        AdditionPersistence expectedResult = AdditionPersistence.builder()
                .id(1L)
                .game_id(1)
                .name("name3")
                .price(10)
                .description("description3")
                .image("image3")
                .build();
        /*when(additionRepository.deleteById(Long.valueOf(1)))
                .thenReturn(expectedResult);*/
        //additionRepository.deleteById(Long.valueOf(1));
        Addition actualResult = deleteAdditionUseCase.DeleteAddition(1);
        assertEquals(expectedResult, actualResult);
        verify(additionRepository).deleteById(Long.valueOf(1));
    }
}