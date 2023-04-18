package com.example.demo.business.cases.gameOrders;

import com.example.demo.domain.Addition;
import com.example.demo.domain.GameOrder;

public interface GetGameOrderUseCase {
    GameOrder GetGameOrder(int index);
}
