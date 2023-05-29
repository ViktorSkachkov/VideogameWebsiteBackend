package com.example.demo.business.impl.additionorder;

import com.example.demo.domain.RankedItem;
import com.example.demo.persistence.repository.AdditionOrderRepository;
import com.example.demo.persistence.repository.AdditionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class GetAdditionOrdersRankedUseCaseImplTest {
    @Mock
    private AdditionOrderRepository additionOrderRepository;
    @Mock
    private AdditionRepository additionRepository;
    @InjectMocks
    private GetAdditionOrdersRankedUseCaseImpl getAdditionOrdersRankedUseCase;

    @Test
    void getAdditionOrdersRankedWith0() {
        RankedItem rankingAdditionOrder = RankedItem.builder()
                .itemId(0)
                .totalIncome(2)
                .units(2)
                .name("Name")
                .build();

        LocalDateTime endDate = LocalDateTime.now().plusDays(1);

        LocalDateTime startDate = LocalDateTime.of(1970, 12, 18, 14, 30, 40);

        when(additionOrderRepository.getUnits(startDate, endDate, true))
                .thenReturn(List.of(2));
        when(additionOrderRepository.getAdditionIds(startDate, endDate, true))
                .thenReturn(List.of(0));
        when(additionOrderRepository.getTotalPrice(startDate, endDate, true))
                .thenReturn(List.of(2.0));
        when(additionRepository.findNameById(Long.valueOf(0)))
                .thenReturn("Name");
        List<RankedItem> actualResult = getAdditionOrdersRankedUseCase.getAdditionOrdersRanked(0, endDate);


        List<RankedItem> expectedResult = new ArrayList<>();
        expectedResult.add(rankingAdditionOrder);

        assertEquals(expectedResult, actualResult);
        verify(additionOrderRepository).getUnits(startDate, endDate, true);
        verify(additionOrderRepository).getAdditionIds(startDate, endDate, true);
        verify(additionOrderRepository).getTotalPrice(startDate, endDate, true);
        verify(additionRepository).findNameById(Long.valueOf(0));
    }
}