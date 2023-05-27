package com.example.demo.business.cases.additionorder;

import com.example.demo.domain.AdditionOrder;
import com.example.demo.domain.RankingAdditionOrder;
import com.example.demo.persistence.entity.AdditionOrderPersistence;

public interface ConfirmAdditionOrderUseCase {
    AdditionOrder confirmAdditionOrder(int userId);

   // RankingAdditionOrder addRankingOrder(AdditionOrderPersistence additionOrderPersistence);
}
