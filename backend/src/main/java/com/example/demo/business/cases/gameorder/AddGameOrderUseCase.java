package com.example.demo.business.cases.gameorder;

import com.example.demo.domain.GameOrder;

public interface AddGameOrderUseCase {
    GameOrder AddGameOrder(GameOrder gameOrder);
}
