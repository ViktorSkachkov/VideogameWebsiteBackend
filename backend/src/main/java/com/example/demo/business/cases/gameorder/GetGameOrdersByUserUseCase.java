package com.example.demo.business.cases.gameorder;

import com.example.demo.domain.GameOrder;

import java.util.List;

public interface GetGameOrdersByUserUseCase {
    List<GameOrder> GetGameOrdersByUser(int userIndex);
}
