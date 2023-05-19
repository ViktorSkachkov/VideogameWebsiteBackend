package com.example.demo.business.impl.additionorder;

import com.example.demo.business.cases.additionorder.DecreaseAdditionOrderUnitsUseCase;
import com.example.demo.persistence.entity.AdditionOrderPersistence;
import com.example.demo.persistence.repository.AdditionOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DecreaseAdditionOrderUnitsUseCaseImpl implements DecreaseAdditionOrderUnitsUseCase {
    private final AdditionOrderRepository additionOrderRepository;

    /**
     * @param additionOrderId
     * @return
     */
    @Override
    public int decreaseAdditionOrderUnits(Long additionOrderId) {
        Optional<AdditionOrderPersistence> additionOrder = additionOrderRepository.findById(additionOrderId);
        if(additionOrder.isEmpty()) {

        }

        int units = additionOrder.get().getUnits();
        units -= 1;
        additionOrder.get().setUnits(units);

        additionOrderRepository.save(additionOrder.get());
        return 0;
    }
}
