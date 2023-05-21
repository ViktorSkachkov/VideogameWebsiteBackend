package com.example.demo.business.impl.gameorder;

import com.example.demo.persistence.entity.GameOrderPersistence;
import com.example.demo.persistence.repository.GameOrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IncreaseGameOrderUnitsUseCaseImplTest {
    @Mock
    private GameOrderRepository gameOrderRepository;
    @InjectMocks
    private IncreaseGameOrderUnitsUseCaseImpl increaseGameOrderUnitsUseCase;

    @Test
    void increaseGameOrderUnits() {
        LocalDateTime time = LocalDateTime.now();

        int expectedResult = 0;
        GameOrderPersistence gameOrder = GameOrderPersistence.builder()
                .id(2)
                .game(41)
                .user(3)
                .units(2)
                .approved(false)
                .time(time)
                .build();

        GameOrderPersistence gameOrder2 = GameOrderPersistence.builder()
                .id(2)
                .game(41)
                .user(3)
                .units(3)
                .approved(false)
                .time(time)
                .build();

        when(gameOrderRepository.findById((long) gameOrder.getId()))
                .thenReturn(Optional.of(gameOrder));
        when(gameOrderRepository.save(gameOrder2))
                .thenReturn(gameOrder2);
        int actualResult = increaseGameOrderUnitsUseCase.increaseGameOrderUnits(gameOrder.getId());
        assertEquals(expectedResult, actualResult);
        verify(gameOrderRepository).findById((long) gameOrder.getId());
        verify(gameOrderRepository).save(gameOrder2);
    }
}