package com.example.demo.business.impl.additionorder;

import com.example.demo.domain.AdditionOrder;
import com.example.demo.domain.RankingAdditionOrder;
import com.example.demo.persistence.entity.AdditionOrderPersistence;
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
import java.time.format.DateTimeFormatter;
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
    void getAdditionOrdersRanked() {
        LocalDateTime time = LocalDateTime.now();

        AdditionOrder additionOrder1 = AdditionOrder.builder()
                .id(0)
                .addition(41)
                .user(41)
                .units(2)
                .totalPrice(0)
                .time(time)
                .build();
        AdditionOrderPersistence additionOrderPersistence1 = AdditionOrderPersistence.builder()
                .id(0)
                .addition(41)
                .user(41)
                .units(2)
                .approved(true)
                .time(time)
                .build();
        RankingAdditionOrder rankingAdditionOrder = RankingAdditionOrder.builder()
                .id(0L)
                .price(0)
                .additionOrderList(List.of(additionOrder1))
                .totalPrice(0)
                .reviewedItemId(41)
                .numberOfTimesBought(2)
                .build();

        when(additionOrderRepository.findAll())
                .thenReturn(List.of(additionOrderPersistence1));
        List<RankingAdditionOrder> actualResult = getAdditionOrdersRankedUseCase.getAdditionOrdersRanked(0);

        List<RankingAdditionOrder> expectedResult = new ArrayList<>();
        expectedResult.add(rankingAdditionOrder);

        assertEquals(expectedResult, actualResult);
        verify(additionOrderRepository).findAll();
    }
}