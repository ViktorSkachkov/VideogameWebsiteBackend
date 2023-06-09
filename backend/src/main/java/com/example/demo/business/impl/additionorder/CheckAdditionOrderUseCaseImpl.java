package com.example.demo.business.impl.additionorder;

import com.example.demo.business.cases.additionorder.CheckAdditionOrderUseCase;
import com.example.demo.persistence.entity.AdditionOrderPersistence;
import com.example.demo.persistence.repository.AdditionOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckAdditionOrderUseCaseImpl implements CheckAdditionOrderUseCase {
    private final AdditionOrderRepository additionOrderRepository;

    @Override
    public boolean CheckAdditionOrder(int id) {
        List<AdditionOrderPersistence> list = additionOrderRepository.findCartItemsByUserId(Long.valueOf(id), false);
        if(list.size() > 0) {
            return true;
        }
        else {
            return false;
        }
    }
}
