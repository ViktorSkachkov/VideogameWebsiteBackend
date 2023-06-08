package com.example.demo.business.cases.additionorder;

import com.example.demo.domain.AdditionOrder;

public interface ConfirmAdditionOrderUseCase {
    AdditionOrder confirmAdditionOrder(int userId);

   // RankingAdditionOrder addRankingOrder(AdditionOrderPersistence additionOrderPersistence);
}
