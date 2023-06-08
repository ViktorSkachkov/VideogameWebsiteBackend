package com.example.demo.business.cases.gameorder;

import com.example.demo.domain.GameOrder;

import java.util.List;

public interface GetGameCartItemsUseCase {
    List<GameOrder> getGameCartItems(int userIndex);

    List<GameOrder> reverseOrder(List<GameOrder> gameOrders);
}
