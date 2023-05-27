package com.example.demo.business.impl.additionorder;

import com.example.demo.business.cases.additionorder.AddAdditionOrderUseCase;
import com.example.demo.domain.AdditionOrder;
import com.example.demo.persistence.entity.AdditionOrderPersistence;
import com.example.demo.persistence.repository.AdditionOrderRepository;
import com.example.demo.persistence.repository.AdditionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddAdditionOrderUseCaseImpl implements AddAdditionOrderUseCase {
    private final AdditionOrderRepository additionOrderRepository;
    private final AdditionRepository additionRepository;

    /**
     * @param additionOrder
     * @return
     */
    @Override
    public AdditionOrder addAdditionOrder(AdditionOrder additionOrder) {
        List<AdditionOrderPersistence> additionOrderPersistenceList = additionOrderRepository.findCartItemsByUserId((long) additionOrder.getUser(), false);

        for(AdditionOrderPersistence element : additionOrderPersistenceList) {
            if(additionOrder.getAddition() == element.getAddition()) {
                int units = element.getUnits() + additionOrder.getUnits();
                double totalPrice = element.getTotalPrice();
                double price = totalPrice / element.getUnits();
                element.setUnits(units);
                element.setTotalPrice(price * units);
                additionOrderRepository.save(element);
                return additionOrder;
            }
        }

        double price = additionRepository.findPriceById((long) additionOrder.getAddition());

        AdditionOrderPersistence additionOrderPersistence = AdditionOrderPersistence.builder()
                .addition(additionOrder.getAddition())
                .user(additionOrder.getUser())
                .units(additionOrder.getUnits())
                .time(LocalDateTime.now())
                .approved(false)
                .totalPrice(price * additionOrder.getUnits())
                .build();
        additionOrderRepository.save(additionOrderPersistence);


        return additionOrder;
    }
}
