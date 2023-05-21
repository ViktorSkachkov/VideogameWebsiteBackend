package com.example.demo.business.impl.additionorder;

import com.example.demo.business.cases.additionorder.DecreaseAdditionOrderUnitsUseCase;
import com.example.demo.domain.AdditionOrder;
import com.example.demo.exception.IsEmptyException;
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
    public AdditionOrder decreaseAdditionOrderUnits(Long additionOrderId) {
        Optional<AdditionOrderPersistence> additionOrder = additionOrderRepository.findById(additionOrderId);
        if(additionOrder.isEmpty()) {
            throw new IsEmptyException();
        }

        if (additionOrder.isPresent()) {
            int units = additionOrder.get().getUnits();
            units -= 1;
            additionOrder.get().setUnits(units);

            additionOrderRepository.save(additionOrder.get());
        }

        return AdditionOrder.builder()
                .id(additionOrder.get().getId())
                .addition(additionOrder.get().getAddition())
                .time(additionOrder.get().getTime())
                .user(additionOrder.get().getUser())
                .approved(additionOrder.get().getApproved())
                .units(additionOrder.get().getUnits())
                .build();
    }
}
