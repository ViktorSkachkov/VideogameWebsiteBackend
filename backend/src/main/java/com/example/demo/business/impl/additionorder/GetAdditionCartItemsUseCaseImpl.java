package com.example.demo.business.impl.additionorder;

import com.example.demo.business.cases.additionorder.GetAdditionCartItemsUseCase;
import com.example.demo.domain.AdditionOrder;
import com.example.demo.persistence.entity.AdditionOrderPersistence;
import com.example.demo.persistence.repository.AdditionOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAdditionCartItemsUseCaseImpl implements GetAdditionCartItemsUseCase {
    private final AdditionOrderRepository additionOrderRepository;

    /**
     * @param userIndex
     * @return
     */
    @Override
    public List<AdditionOrder> getAdditionCartItems(int userIndex) {
        List<AdditionOrderPersistence> list = additionOrderRepository.findAll();
        List<AdditionOrder> additionOrders = new ArrayList<>();
        for (AdditionOrderPersistence aop : list) {

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDateTime = aop.getTime().format(dateTimeFormatter);


            if (aop.getUser() == userIndex && !aop.getApproved()) {
                AdditionOrder additionOrder = AdditionOrder.builder()
                        .id(Math.toIntExact(aop.getId()))
                        .addition(aop.getAddition())
                        .user(aop.getUser())
                        .units(aop.getUnits())
                        .time(aop.getTime())
                        .dateFormatted(formattedDateTime)
                        .approved(aop.getApproved())
                        .build();
                additionOrders.add(additionOrder);
            }
        }

        List<AdditionOrder> newList = reverseOrder(additionOrders);
        return newList;
    }

    /**
     * @param additionOrders
     * @return
     */
    @Override
    public List<AdditionOrder> reverseOrder(List<AdditionOrder> additionOrders) {
        List<AdditionOrder> result = new ArrayList<>();

        for (int i = additionOrders.size() - 1; i >= 0; i--) {
            result.add(additionOrders.get(i));
        }

        return result;
    }
}
