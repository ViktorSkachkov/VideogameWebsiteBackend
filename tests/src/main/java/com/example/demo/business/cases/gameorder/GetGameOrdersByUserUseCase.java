package com.example.demo.business.cases.gameorder;

import com.example.demo.domain.GameOrder;

import java.util.List;

public interface GetGameOrdersByUserUseCase {
    List<GameOrder> getGameOrdersByUser(int userIndex);

    List<GameOrder> reverseOrder(List<GameOrder> gameOrders);
}
