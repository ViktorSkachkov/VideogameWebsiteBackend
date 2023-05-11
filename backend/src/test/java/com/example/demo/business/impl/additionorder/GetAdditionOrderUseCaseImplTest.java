package com.example.demo.business.impl.additionorder;

import com.example.demo.domain.AdditionOrder;
import com.example.demo.persistence.entity.AdditionOrderPersistence;
import com.example.demo.persistence.repository.AdditionOrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAdditionOrderUseCaseImplTest {
    @Mock
    private AdditionOrderRepository additionOrderRepository;
    @InjectMocks
    private GetAdditionOrderUseCaseImpl getAdditionOrderUseCase;

    @Test
    void getAdditionOrder() {
        AdditionOrder expectedResult = AdditionOrder.builder()
                .id(1)
                .addition(41)
                .user(41)
                .units(2)
                .build();
        AdditionOrderPersistence additionOrder = AdditionOrderPersistence.builder()
                .id(1)
                .addition(41)
                .user(41)
                .units(2)
                .build();
        when(additionOrderRepository.findById(1L))
                .thenReturn(Optional.ofNullable(additionOrder));
        AdditionOrder actualResult = getAdditionOrderUseCase.getAdditionOrder(1);
        assertEquals(expectedResult, actualResult);
        verify(additionOrderRepository).findById(1L);
    }
}