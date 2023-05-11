package com.example.demo.business.impl.gameorder;

import com.example.demo.domain.GameOrder;
import com.example.demo.persistence.domain.persistenceClass.GameOrderPersistence;
import com.example.demo.persistence.repository.GameOrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetGameOrderUseCaseImplTest {
    @Mock
    private GameOrderRepository gameOrderRepository;
    @InjectMocks
    private GetGameOrderUseCaseImpl getGameOrderUseCase;

    @Test
    void getGameOrder() {
        GameOrder expectedResult = GameOrder.builder()
                .id(1)
                .game(23)
                .user(41)
                .units(2)
                .build();
        GameOrderPersistence gameOrder = GameOrderPersistence.builder()
                .id(1)
                .game(23)
                .user(41)
                .units(2)
                .build();
        when(gameOrderRepository.findById(1L))
                .thenReturn(Optional.ofNullable(gameOrder));
        GameOrder actualResult = getGameOrderUseCase.getGameOrder(1);
        assertEquals(expectedResult, actualResult);
        verify(gameOrderRepository).findById(1L);
    }
}