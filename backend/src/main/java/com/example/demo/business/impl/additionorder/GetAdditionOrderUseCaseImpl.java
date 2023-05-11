package com.example.demo.business.impl.additionorder;

import com.example.demo.business.cases.additionorder.GetAdditionOrderUseCase;
import com.example.demo.domain.AdditionOrder;
import com.example.demo.persistence.entity.AdditionOrderPersistence;
import com.example.demo.persistence.repository.AdditionOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetAdditionOrderUseCaseImpl implements GetAdditionOrderUseCase {
    private final AdditionOrderRepository additionOrderRepository;

    /**
     *
     * @param index
     * @return
     */
    @Override
    public AdditionOrder getAdditionOrder(int index) {
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
