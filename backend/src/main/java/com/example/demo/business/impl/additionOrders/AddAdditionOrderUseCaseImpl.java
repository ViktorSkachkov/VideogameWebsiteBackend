package com.example.demo.business.impl.additionOrders;

import com.example.demo.business.cases.additionOrders.AddAdditionOrderUseCase;
import com.example.demo.domain.AdditionOrder;
import com.example.demo.domain.persistenceClasses.AdditionOrderPersistence;
import com.example.demo.persistence.repositories.AdditionOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddAdditionOrderUseCaseImpl implements AddAdditionOrderUseCase {
    private final AdditionOrderRepository additionOrderRepository;

    @Override
    public AdditionOrder AddAdditionOrder(AdditionOrder additionOrder) {
        AdditionOrderPersistence additionOrderPersistence = AdditionOrderPersistence.builder()
                .addition(additionOrder.getAddition())
                .user(additionOrder.getUser())
                .units(additionOrder.getUnits())
                .build();
        additionOrderRepository.save(additionOrderPersistence);
        return additionOrder;
    }
}
