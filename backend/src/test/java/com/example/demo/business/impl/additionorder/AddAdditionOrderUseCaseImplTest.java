package com.example.demo.business.impl.additionorder;

import com.example.demo.domain.AdditionOrder;
import com.example.demo.persistence.entity.AdditionOrderPersistence;
import com.example.demo.persistence.repository.AdditionOrderRepository;
import com.example.demo.persistence.repository.AdditionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddAdditionOrderUseCaseImplTest {
    @Mock
    private AdditionOrderRepository additionOrderRepository;
    @Mock
    private AdditionRepository additionRepository;
    @InjectMocks
    private AddAdditionOrderUseCaseImpl addAdditionOrderUseCase;

    @Test
    void addAdditionOrder() {
        LocalDateTime time = LocalDateTime.now();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDateTime = time.format(dateTimeFormatter);

        AdditionOrder expectedResult = AdditionOrder.builder()
                .id(1)
                .addition(41)
                .user(41)
                .units(2)
                .approved(false)
                .time(time)
                .totalPrice(48)
                .dateFormatted(formattedDateTime)
                .build();
        AdditionOrderPersistence additionOrder = AdditionOrderPersistence.builder()
                .addition(41)
                .user(41)
                .units(2)
                .approved(false)
                .time(time)
                .totalPrice(48)
                .build();
        when(additionRepository.findPriceById((long) additionOrder.getAddition()))
                .thenReturn(additionOrder.getTotalPrice());
        when(additionOrderRepository.save(additionOrder))
                .thenReturn(additionOrder);
        AdditionOrder actualResult = addAdditionOrderUseCase.addAdditionOrder(expectedResult);
        assertEquals(expectedResult, actualResult);
        verify(additionOrderRepository).save(additionOrder);
    }
}