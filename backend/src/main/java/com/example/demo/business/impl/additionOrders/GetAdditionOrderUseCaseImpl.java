package com.example.demo.business.impl.additionOrders;

import com.example.demo.business.cases.additionOrders.GetAdditionOrderUseCase;
import com.example.demo.domain.AdditionOrder;
import com.example.demo.domain.GameOrder;
import com.example.demo.domain.persistenceClasses.AdditionOrderPersistence;
import com.example.demo.persistence.repositories.AdditionOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetAdditionOrderUseCaseImpl implements GetAdditionOrderUseCase {
    private final AdditionOrderRepository additionOrderRepository;

    @Override
    public AdditionOrder GetAdditionOrder(int index) {
        Optional<AdditionOrderPersistence> aop = additionOrderRepository.findById(Long.valueOf(index));
        if(aop.isEmpty()) {

        }
        AdditionOrder additionOrder = AdditionOrder.builder()
                .id(Math.toIntExact(aop.get().getId()))
                .user(aop.get().getUser())
                .addition(aop.get().getAddition())
                .units(aop.get().getUnits())
                .build();
        return additionOrder;
    }
}
