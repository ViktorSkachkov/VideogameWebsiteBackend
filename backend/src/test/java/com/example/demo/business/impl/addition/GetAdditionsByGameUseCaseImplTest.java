package com.example.demo.business.impl.addition;

import com.example.demo.domain.Addition;
import com.example.demo.persistence.domain.persistenceClass.AdditionPersistence;
import com.example.demo.persistence.repository.AdditionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAdditionsByGameUseCaseImplTest {
    @Mock
    private AdditionRepository additionRepository;
    @InjectMocks
    private GetAdditionsByGameUseCaseImpl getAdditionsByGameUseCase;

    @Test
    void getAdditionsByGame() {
        Addition addition1 = Addition.builder()
                .id(1)
                .gameId(1)
                .name("name1")
                .price(10)
                .description("description1")
                .image("image1")
                .build();
        /*Addition addition2 = Addition.builder()
                .id(2)
                .gameId(1)
                .name("name2")
                .price(10)
                .description("description2")
                .image("image2")
                .build();*/
        AdditionPersistence additionPersistence1 = AdditionPersistence.builder()
                .id(1L)
                .game_id(1)
                .name("name1")
                .price(10)
                .description("description1")
                .image("image1")
                .build();
        AdditionPersistence additionPersistence2 = AdditionPersistence.builder()
                .id(2L)
                .game_id(2)
                .name("name2")
                .price(10)
                .description("description2")
                .image("image2")
                .build();
        when(additionRepository.findAll())
                .thenReturn(List.of(additionPersistence1, additionPersistence2));
        List<Addition> actualResult = getAdditionsByGameUseCase.GetAdditionsByGame(1);
        List<Addition> expectedResult = new ArrayList<>();
        expectedResult.add(addition1);
        assertEquals(expectedResult, actualResult);
        verify(additionRepository).findAll();
    }
}