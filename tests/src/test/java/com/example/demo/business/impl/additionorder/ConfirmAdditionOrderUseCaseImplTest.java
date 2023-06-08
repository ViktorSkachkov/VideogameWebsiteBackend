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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConfirmAdditionOrderUseCaseImplTest {
    @Mock
    private AdditionOrderRepository additionOrderRepository;
    @InjectMocks
    private ConfirmAdditionOrderUseCaseImpl confirmAdditionOrderUseCase;

    @Test
    void confirmAdditionOrder() {
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

        when(additionOrderRepository.findByUserId((long) expectedResult.getUser()))
                .thenReturn(List.of(additionOrder));
        when(additionOrderRepository.save(additionOrder))
                .thenReturn(additionOrder);
        AdditionOrder actualResult1 = confirmAdditionOrderUseCase.confirmAdditionOrder(3);

        assertEquals(expectedResult.getId(), actualResult1.getId());
        assertEquals(expectedResult.getAddition(), actualResult1.getAddition());
        assertEquals(expectedResult.getUser(), actualResult1.getUser());
        assertEquals(expectedResult.getUnits(), actualResult1.getUnits());
        verify(additionOrderRepository).findByUserId(3L);
    }
}