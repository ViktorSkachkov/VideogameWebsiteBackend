package com.example.demo.business.impl.additionorder;

import com.example.demo.domain.AdditionOrder;
import com.example.demo.persistence.entity.AdditionOrderPersistence;
import com.example.demo.persistence.repository.AdditionOrderRepository;
import com.example.demo.persistence.repository.AdditionRepository;
import com.example.demo.persistence.repository.GameOrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAdditionCartItemsUseCaseImplTest {
    @Mock
    private AdditionOrderRepository additionOrderRepository;
    @Mock
    private AdditionRepository additionRepository;
    @InjectMocks
    private GetAdditionCartItemsUseCaseImpl getAdditionCartItemsUseCase;

    @Test
    void getAdditionCartItemsUseCase() {
        LocalDateTime time = LocalDateTime.now();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDateTime = time.format(dateTimeFormatter);

        AdditionOrder additionOrder1 = AdditionOrder.builder()
                .id(1)
                .addition(23)
                .user(41)
                .units(2)
                .totalPrice(0)
                .approved(false)
                .time(time)
                .dateFormatted(formattedDateTime)
                .build();

        AdditionOrderPersistence additionOrderPersistence1 = AdditionOrderPersistence.builder()
                .id(1)
                .addition(23)
                .user(41)
                .units(2)
                .approved(false)
                .time(time)
                .build();
        AdditionOrderPersistence additionOrderPersistence2 = AdditionOrderPersistence.builder()
                .id(2)
                .addition(23)
                .user(20)
                .units(2)
                .approved(false)
                .time(time)
                .build();
        when(additionOrderRepository.findAll())
                .thenReturn(List.of(additionOrderPersistence1, additionOrderPersistence2));
        List<AdditionOrder> actualResult = getAdditionCartItemsUseCase.getAdditionCartItems(41);
        List<AdditionOrder> expectedResult = new ArrayList<>();
        expectedResult.add(additionOrder1);
        assertEquals(expectedResult, actualResult);
        verify(additionOrderRepository).findAll();
    }
}