package com.example.demo.business.cases.additionOrders;

import com.example.demo.domain.AdditionOrder;

import java.util.List;

public interface GetAdditionOrdersByUserUseCase {
    List<AdditionOrder> GetAdditionOrdersByUser(int gameIndex);
}
