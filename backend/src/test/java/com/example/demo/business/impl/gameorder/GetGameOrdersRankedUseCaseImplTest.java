package com.example.demo.business.impl.gameorder;

import com.example.demo.domain.GameOrder;
import com.example.demo.domain.RankedItem;
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
    void getGameOrdersRankedWith0() {
        RankedItem rankingAdditionOrder = RankedItem.builder()
                .itemId(0)
                .totalIncome(2)
                .units(2)
                .name("Name")
                .build();

        LocalDateTime endDate = LocalDateTime.now().plusDays(1);

        LocalDateTime startDate = LocalDateTime.of(1970, 12, 18, 14, 30, 40);

        when(gameOrderRepository.getUnits(startDate, endDate, true))
                .thenReturn(List.of(2));
        when(gameOrderRepository.getGameIds(startDate, endDate, true))
                .thenReturn(List.of(0));
        when(gameOrderRepository.getTotalPrice(startDate, endDate, true))
                .thenReturn(List.of(2.0));
        when(videogameRepository.findNameById(Long.valueOf(0)))
                .thenReturn("Name");
        List<RankedItem> actualResult = getGameOrdersRankedUseCase.getGameOrdersRanked(0, endDate);


        List<RankedItem> expectedResult = new ArrayList<>();
        expectedResult.add(rankingAdditionOrder);

        assertEquals(expectedResult, actualResult);
        verify(gameOrderRepository).getUnits(startDate, endDate, true);
        verify(gameOrderRepository).getGameIds(startDate, endDate, true);
        verify(gameOrderRepository).getTotalPrice(startDate, endDate, true);
        verify(videogameRepository).findNameById(Long.valueOf(0));

    }
}