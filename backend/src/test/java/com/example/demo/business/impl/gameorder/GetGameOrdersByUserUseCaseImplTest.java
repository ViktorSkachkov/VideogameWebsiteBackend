package com.example.demo.business.impl.gameorder;

import com.example.demo.domain.GameOrder;
import com.example.demo.persistence.domain.persistenceClass.GameOrderPersistence;
import com.example.demo.persistence.repository.GameOrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetGameOrdersByUserUseCaseImplTest {
    @Mock
    private GameOrderRepository gameOrderRepository;
    @InjectMocks
    private GetGameOrdersByUserUseCaseImpl getGameOrdersByUserUseCase;

    @Test
    void getGameOrdersByUser() {
        GameOrder additionOrder1 = GameOrder.builder()
                .id(1)
                .game(23)
                .user(41)
                .units(2)
                .build();
        GameOrderPersistence gameOrderPersistence1 = GameOrderPersistence.builder()
                .id(1)
                .game(23)
                .user(41)
                .units(2)
                .build();
        GameOrderPersistence gameOrderPersistence2 = GameOrderPersistence.builder()
                .id(2)
                .game(23)
                .user(20)
                .units(2)
                .build();
        when(gameOrderRepository.findAll())
                .thenReturn(List.of(gameOrderPersistence1, gameOrderPersistence2));
        List<GameOrder> actualResult = getGameOrdersByUserUseCase.GetGameOrdersByUser(41);
        List<GameOrder> expectedResult = new ArrayList<>();
        expectedResult.add(additionOrder1);
        assertEquals(expectedResult, actualResult);
        verify(gameOrderRepository).findAll();
    }
}