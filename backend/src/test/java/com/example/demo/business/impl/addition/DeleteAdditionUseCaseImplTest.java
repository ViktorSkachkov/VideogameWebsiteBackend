package com.example.demo.business.impl.addition;

import com.example.demo.domain.Addition;
import com.example.demo.persistence.entity.AdditionPersistence;
import com.example.demo.persistence.repository.AdditionOrderRepository;
import com.example.demo.persistence.repository.AdditionRepository;
import com.example.demo.persistence.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteAdditionUseCaseImplTest {
    @Mock
    private AdditionRepository additionRepository;
    @Mock
    private AdditionOrderRepository additionOrderRepository;
    @Mock
    private ReviewRepository reviewRepository;
    @InjectMocks
    private DeleteAdditionUseCaseImpl deleteAdditionUseCase;

    @Test
    void deleteAddition() {
        Addition expectedResult = Addition.builder()
                .id(1)
                .gameId(1)
                .name("name1")
                .price(10)
                .description("description1")
                .image("image1")
                .build();
        AdditionPersistence addition = AdditionPersistence.builder()
                .id(1L)
                .game_id(1)
                .name("name1")
                .price(10)
                .description("description1")
                .image("image1")
                .build();
        when(additionRepository.findById(1L))
                .thenReturn(Optional.ofNullable(addition));
        Addition actualResult = deleteAdditionUseCase.deleteAddition(1);
        assertEquals(expectedResult, actualResult);
        verify(additionRepository).deleteById(Long.valueOf(1));
    }
}