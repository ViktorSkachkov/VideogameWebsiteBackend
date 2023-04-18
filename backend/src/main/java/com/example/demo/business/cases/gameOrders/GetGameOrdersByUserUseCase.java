package com.example.demo.business.cases.gameOrders;

import com.example.demo.domain.GameOrder;

import java.util.List;

public interface GetGameOrdersByUserUseCase {
    List<GameOrder> GetGameOrdersByUser(int gameIndex);
}
