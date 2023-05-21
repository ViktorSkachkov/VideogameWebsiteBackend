package com.example.demo.business.impl.gameorder;

import com.example.demo.persistence.entity.GameOrderPersistence;
import com.example.demo.persistence.repository.GameOrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConfirmGameOrderUseCaseImplTest {
    @Mock
    private GameOrderRepository gameOrderRepository;
    @InjectMocks
    private ConfirmGameOrderUseCaseImpl confirmGameOrderUseCase;

    @Test
    void confirmGameOrder() {
        LocalDateTime time = LocalDateTime.now();

        int expectedResult = 3;
        GameOrderPersistence gameOrder = GameOrderPersistence.builder()
                .id(2)
                .game(41)
                .user(3)
                .units(2)
                .approved(false)
                .time(time)
                .build();

        when(gameOrderRepository.findByUserId((long) expectedResult))
                .thenReturn(List.of(gameOrder));
        when(gameOrderRepository.save(gameOrder))
                .thenReturn(gameOrder);
        int actualResult1 = confirmGameOrderUseCase.confirmGameOrder(3);

        assertEquals(expectedResult, actualResult1);
        verify(gameOrderRepository).save(gameOrder);
        verify(gameOrderRepository).findByUserId(3L);
    }
}