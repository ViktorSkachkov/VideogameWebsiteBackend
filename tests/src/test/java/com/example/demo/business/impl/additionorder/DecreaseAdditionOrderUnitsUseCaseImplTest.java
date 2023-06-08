package com.example.demo.business.impl.additionorder;

import com.example.demo.domain.AdditionOrder;
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
class DecreaseAdditionOrderUnitsUseCaseImplTest {
    @Mock
    private AdditionOrderRepository additionOrderRepository;
    @InjectMocks
    private DecreaseAdditionOrderUnitsUseCaseImpl decreaseAdditionOrderUnitsUseCase;

    @Test
    void decreaseGameOrderUnits() {
        LocalDateTime time = LocalDateTime.now();

        AdditionOrder expectedResult = AdditionOrder.builder()
                .id(2)
                .addition(41)
                .user(3)
                .units(2)
                .approved(false)
                .time(time)
                .build();
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
                .units(1)
                .approved(false)
                .time(time)
                .build();

        when(additionOrderRepository.findById((long) additionOrder.getId()))
                .thenReturn(Optional.of(additionOrder));
        when(additionOrderRepository.save(additionOrder2))
                .thenReturn(additionOrder2);
        AdditionOrder actualResult = decreaseAdditionOrderUnitsUseCase.decreaseAdditionOrderUnits((long) additionOrder.getId());
        assertEquals(expectedResult.getId(), actualResult.getId());
        assertEquals(expectedResult.getAddition(), actualResult.getAddition());
        assertEquals(expectedResult.getUnits() - 1, actualResult.getUnits());
        assertEquals(expectedResult.getUser(), actualResult.getUser());
        assertEquals(expectedResult.getApproved(), actualResult.getApproved());
        assertEquals(expectedResult.getTime(), actualResult.getTime());

        verify(additionOrderRepository).findById((long) additionOrder.getId());
        verify(additionOrderRepository).save(additionOrder2);
    }
}