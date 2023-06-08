package com.example.demo.business.impl.gameorder;

import com.example.demo.domain.RankedItem;
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

        when(gameOrderRepository.getRankedGameItems(startDate, endDate, true))
                .thenReturn(List.of(rankingAdditionOrder));
        when(videogameRepository.findNameById(Long.valueOf(0)))
                .thenReturn("Name");
        List<RankedItem> actualResult = getGameOrdersRankedUseCase.getGameOrdersRanked(0, endDate);


        List<RankedItem> expectedResult = new ArrayList<>();
        expectedResult.add(rankingAdditionOrder);

        assertEquals(expectedResult, actualResult);

        verify(gameOrderRepository).getRankedGameItems(startDate, endDate, true);
        verify(videogameRepository).findNameById(Long.valueOf(0));

    }
}