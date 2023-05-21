package com.example.demo.business.impl.gameorder;

import com.example.demo.domain.GameOrder;
import com.example.demo.domain.RankingGameOrder;
import com.example.demo.persistence.entity.GameOrderPersistence;
import com.example.demo.persistence.repository.GameOrderRepository;
import com.example.demo.persistence.repository.VideogameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetGameOrdersRankedUseCaseImplTest {
    @Mock
    private GameOrderRepository gameOrderRepository;
    @Mock
    private VideogameRepository videogameRepository;
    @InjectMocks
    private GetGameOrdersRankedUseCaseImpl getGameOrdersRankedUseCase;

    @Test
    void getGameOrdersRanked() {
        LocalDateTime time = LocalDateTime.now();

        GameOrder gameOrder1 = GameOrder.builder()
                .id(0)
                .game(41)
                .user(41)
                .units(2)
                .totalPrice(0)
                .time(time)
                .build();
        GameOrderPersistence gameOrderPersistence1 = GameOrderPersistence.builder()
                .id(0)
                .game(41)
                .user(41)
                .units(2)
                .approved(true)
                .time(time)
                .build();
        RankingGameOrder rankingGameOrder = RankingGameOrder.builder()
                .id(0L)
                .price(0)
                .gameOrderList(List.of(gameOrder1))
                .totalPrice(0)
                .reviewedItemId(41)
                .numberOfTimesBought(2)
                .build();

        when(gameOrderRepository.findAll())
                .thenReturn(List.of(gameOrderPersistence1));
        List<RankingGameOrder> actualResult = getGameOrdersRankedUseCase.getGameOrdersRanked(0);

        List<RankingGameOrder> expectedResult = new ArrayList<>();
        expectedResult.add(rankingGameOrder);

        assertEquals(expectedResult, actualResult);
        verify(gameOrderRepository).findAll();
    }
}