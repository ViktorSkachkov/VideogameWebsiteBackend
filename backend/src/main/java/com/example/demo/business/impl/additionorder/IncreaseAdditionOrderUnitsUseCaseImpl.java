package com.example.demo.business.impl.additionorder;

import com.example.demo.business.cases.additionorder.IncreaseAdditionOrderUnitsUseCase;
import com.example.demo.exception.IsEmptyException;
import com.example.demo.persistence.entity.AdditionOrderPersistence;
import com.example.demo.persistence.repository.AdditionOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IncreaseAdditionOrderUnitsUseCaseImpl implements IncreaseAdditionOrderUnitsUseCase {
    private final AdditionOrderRepository additionOrderRepository;

    /**
     * @param additionOrderId
     * @return
     */
    @Override
    public int increaseAdditionOrderUnits(Long additionOrderId) {
        Optional<AdditionOrderPersistence> additionOrder = additionOrderRepository.findById(additionOrderId);
        if(additionOrder.isEmpty()) {
            throw new IsEmptyException();
        }

        if (additionOrder.isPresent()) {
            int units = additionOrder.get().getUnits();
            units += 1;
            additionOrder.get().setUnits(units);

            additionOrderRepository.save(additionOrder.get());
        }

        return 0;
    }
}
