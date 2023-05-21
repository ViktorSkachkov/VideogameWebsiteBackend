package com.example.demo.business.impl.gameorder;

import com.example.demo.domain.GameOrder;
import com.example.demo.persistence.entity.GameOrderPersistence;
import com.example.demo.persistence.repository.GameOrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteGameOrderUseCaseImplTest {
    @Mock
    private GameOrderRepository gameOrderRepository;
    @InjectMocks
    private DeleteGameOrderUseCaseImpl deleteGameOrderUseCase;

    @Test
    void deleteGameOrder() {
        LocalDateTime time = LocalDateTime.now();

        GameOrder expectedResult = GameOrder.builder()
                .id(2)
                .game(41)
                .user(3)
                .units(2)
                .approved(false)
                .time(time)
                .build();
        GameOrderPersistence gameOrder = GameOrderPersistence.builder()
                .id(2)
                .game(41)
                .user(3)
                .units(2)
                .approved(false)
                .time(time)
                .build();

        GameOrder actualResult = deleteGameOrderUseCase.deleteGame(gameOrder.getId());

        assertEquals(expectedResult.getId(), actualResult.getId());
        verify(gameOrderRepository).deleteById(Long.valueOf(2));
    }
}