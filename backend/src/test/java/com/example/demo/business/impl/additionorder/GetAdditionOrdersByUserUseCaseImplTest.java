package com.example.demo.business.impl.additionorder;

import com.example.demo.domain.AdditionOrder;
import com.example.demo.persistence.domain.persistenceClass.AdditionOrderPersistence;
import com.example.demo.persistence.repository.AdditionOrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAdditionOrdersByUserUseCaseImplTest {
    @Mock
    private AdditionOrderRepository additionOrderRepository;
    @InjectMocks
    private GetAdditionOrdersByUserUseCaseImpl getAdditionOrdersByUserUseCase;

    @Test
    void getAdditionOrdersByUser() {
        AdditionOrder additionOrder1 = AdditionOrder.builder()
                .id(1)
                .addition(41)
                .user(41)
                .units(2)
                .build();
        AdditionOrderPersistence additionOrderPersistence1 = AdditionOrderPersistence.builder()
                .id(1)
                .addition(41)
                .user(41)
                .units(2)
                .build();
        AdditionOrderPersistence additionOrderPersistence2 = AdditionOrderPersistence.builder()
                .id(2)
                .addition(41)
                .user(20)
                .units(2)
                .build();
        when(additionOrderRepository.findAll())
                .thenReturn(List.of(additionOrderPersistence1, additionOrderPersistence2));
        List<AdditionOrder> actualResult = getAdditionOrdersByUserUseCase.getAdditionOrdersByUser(41);
        List<AdditionOrder> expectedResult = new ArrayList<>();
        expectedResult.add(additionOrder1);
        assertEquals(expectedResult, actualResult);
        verify(additionOrderRepository).findAll();
    }
}