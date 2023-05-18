package com.example.demo.business.cases.additionorder;

import com.example.demo.domain.AdditionOrder;

import java.util.List;

public interface GetAdditionCartItemsUseCase {
    List<AdditionOrder> getAdditionCartItems(int userIndex);

    List<AdditionOrder> reverseOrder(List<AdditionOrder> additionOrders);
}
