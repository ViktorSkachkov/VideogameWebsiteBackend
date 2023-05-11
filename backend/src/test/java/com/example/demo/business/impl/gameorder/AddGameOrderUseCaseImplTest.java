package com.example.demo.business.impl.gameorder;

import com.example.demo.domain.GameOrder;
import com.example.demo.persistence.entity.GameOrderPersistence;
import com.example.demo.persistence.repository.GameOrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddGameOrderUseCaseImplTest {
    @Mock
    private GameOrderRepository gameOrderRepository;
    @InjectMocks
    private AddGameOrderUseCaseImpl addGameOrderUseCase;

    @Test
    void addGameOrder() {
        GameOrder expectedResult = GameOrder.builder()
                .id(1)
                .game(23)
                .user(41)
                .units(2)
                .build();
        GameOrderPersistence gameOrder = GameOrderPersistence.builder()
                .game(23)
                .user(41)
                .units(2)
                .build();
        when(gameOrderRepository.save(gameOrder))
                .thenReturn(gameOrder);
        GameOrder actualResult = addGameOrderUseCase.addGameOrder(expectedResult);
        assertEquals(expectedResult, actualResult);
        verify(gameOrderRepository).save(gameOrder);
    }
}