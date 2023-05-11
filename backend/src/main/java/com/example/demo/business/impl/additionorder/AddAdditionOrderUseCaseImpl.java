package com.example.demo.business.impl.additionorder;

import com.example.demo.business.cases.additionorder.AddAdditionOrderUseCase;
import com.example.demo.domain.AdditionOrder;
import com.example.demo.persistence.domain.persistenceClass.AdditionOrderPersistence;
import com.example.demo.persistence.repository.AdditionOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddAdditionOrderUseCaseImpl implements AddAdditionOrderUseCase {
    private final AdditionOrderRepository additionOrderRepository;

    /**
     *
     * @param additionOrder
     * @return
     */
    @Override
    public AdditionOrder addAdditionOrder(AdditionOrder additionOrder) {
        AdditionOrderPersistence additionOrderPersistence = AdditionOrderPersistence.builder()
                .addition(additionOrder.getAddition())
                .user(additionOrder.getUser())
                .units(additionOrder.getUnits())
                .build();
        additionOrderRepository.save(additionOrderPersistence);
        return additionOrder;
    }
}
