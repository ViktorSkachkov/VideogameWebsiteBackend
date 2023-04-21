package com.example.demo.business.cases.additionOrders;

import com.example.demo.domain.AdditionOrder;

public interface GetAdditionOrderUseCase {
    AdditionOrder GetAdditionOrder(int index);
}
