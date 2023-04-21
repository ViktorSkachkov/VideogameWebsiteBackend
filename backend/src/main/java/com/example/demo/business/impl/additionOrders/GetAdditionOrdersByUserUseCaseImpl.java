package com.example.demo.business.impl.additionOrders;

import com.example.demo.business.cases.additionOrders.GetAdditionOrdersByUserUseCase;
import com.example.demo.domain.AdditionOrder;
import com.example.demo.domain.GameOrder;
import com.example.demo.domain.persistenceClasses.AdditionOrderPersistence;
import com.example.demo.persistence.repositories.AdditionOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class GetAdditionOrdersByUserUseCaseImpl implements GetAdditionOrdersByUserUseCase {
    private final AdditionOrderRepository additionOrderRepository;

    @Override
    public List<AdditionOrder> GetAdditionOrdersByUser(int userIndex) {
        List<AdditionOrderPersistence> list = additionOrderRepository.findAll();
        List<AdditionOrder> additionOrders = new ArrayList<>();
        for(AdditionOrderPersistence aop : list) {
            if(aop.getUser() == userIndex) {
                AdditionOrder additionOrder = AdditionOrder.builder()
                        .id(Math.toIntExact(aop.getId()))
                        .addition(aop.getAddition())
                        .user(aop.getUser())
                        .units(aop.getUnits())
                        .build();
                additionOrders.add(additionOrder);
            }
        }
        return additionOrders;
    }
}
