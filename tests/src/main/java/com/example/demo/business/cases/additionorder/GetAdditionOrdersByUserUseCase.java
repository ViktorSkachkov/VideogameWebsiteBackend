package com.example.demo.business.cases.additionorder;

import com.example.demo.domain.AdditionOrder;

import java.util.List;

public interface GetAdditionOrdersByUserUseCase {
    List<AdditionOrder> getAdditionOrdersByUser(int gameIndex);

    List<AdditionOrder> reverseOrder(List<AdditionOrder> additionOrders);
}
