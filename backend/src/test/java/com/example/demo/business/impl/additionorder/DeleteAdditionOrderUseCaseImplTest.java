package com.example.demo.business.impl.additionorder;

import com.example.demo.persistence.entity.AdditionOrderPersistence;
import com.example.demo.persistence.repository.AdditionOrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteAdditionOrderUseCaseImplTest {
    @Mock
    private AdditionOrderRepository additionOrderRepository;
    @InjectMocks
    private DeleteAdditionOrderUseCaseImpl deleteAdditionOrderUseCase;

    @Test
    void deleteAdditionOrder() {
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

        int actualResult = deleteAdditionOrderUseCase.deleteAddition(additionOrder.getId());

        assertEquals(expectedResult, actualResult);
        verify(additionOrderRepository).deleteById(Long.valueOf(2));
    }
}