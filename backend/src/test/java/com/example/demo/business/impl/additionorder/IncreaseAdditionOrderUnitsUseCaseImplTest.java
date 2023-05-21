package com.example.demo.business.impl.additionorder;

import com.example.demo.persistence.entity.AdditionOrderPersistence;
import com.example.demo.persistence.repository.AdditionOrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IncreaseAdditionOrderUnitsUseCaseImplTest {
    @Mock
    private AdditionOrderRepository additionOrderRepository;
    @InjectMocks
    private IncreaseAdditionOrderUnitsUseCaseImpl increaseAdditionOrderUnitsUseCase;

    @Test
    void increaseGameOrderUnits() {
        LocalDateTime time = LocalDateTime.now();

        int expectedResult = 0;
        AdditionOrderPersistence additionOrder = AdditionOrderPersistence.builder()
                .id(2)
                .addition(41)
                .user(3)
                .units(2)
                .approved(false)
                .time(time)
                .build();

        AdditionOrderPersistence additionOrder2 = AdditionOrderPersistence.builder()
                .id(2)
                .addition(41)
                .user(3)
                .units(3)
                .approved(false)
                .time(time)
                .build();

        when(additionOrderRepository.findById((long) additionOrder.getId()))
                .thenReturn(Optional.of(additionOrder));
        when(additionOrderRepository.save(additionOrder2))
                .thenReturn(additionOrder2);
        int actualResult = increaseAdditionOrderUnitsUseCase.increaseAdditionOrderUnits((long) additionOrder.getId());
        assertEquals(expectedResult, actualResult);
        verify(additionOrderRepository).findById((long) additionOrder.getId());
        verify(additionOrderRepository).save(additionOrder2);
    }
}