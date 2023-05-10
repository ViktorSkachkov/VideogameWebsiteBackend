package com.example.demo.business.impl.additionorder;

import com.example.demo.domain.AdditionOrder;
import com.example.demo.persistence.domain.persistenceClass.AdditionOrderPersistence;
import com.example.demo.persistence.repository.AdditionOrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddAdditionOrderUseCaseImplTest {
    @Mock
    private AdditionOrderRepository additionOrderRepository;
    @InjectMocks
    private AddAdditionOrderUseCaseImpl addAdditionOrderUseCase;

    @Test
    void addAdditionOrder() {
        AdditionOrder expectedResult = AdditionOrder.builder()
                .id(1)
                .addition(41)
                .user(41)
                .units(2)
                .build();
        AdditionOrderPersistence additionOrder = AdditionOrderPersistence.builder()
                .addition(41)
                .user(41)
                .units(2)
                .build();
        when(additionOrderRepository.save(additionOrder))
                .thenReturn(additionOrder);
        AdditionOrder actualResult = addAdditionOrderUseCase.AddAdditionOrder(expectedResult);
        assertEquals(expectedResult, actualResult);
        verify(additionOrderRepository).save(additionOrder);
    }
}