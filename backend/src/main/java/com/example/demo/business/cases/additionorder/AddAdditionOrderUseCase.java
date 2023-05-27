package com.example.demo.business.cases.additionorder;

import com.example.demo.domain.AdditionOrder;
import com.example.demo.domain.RankingAdditionOrder;
import com.example.demo.persistence.entity.AdditionOrderPersistence;
import com.example.demo.persistence.entity.RankingAdditionOrderPersistence;

public interface AddAdditionOrderUseCase {
    AdditionOrder addAdditionOrder(AdditionOrder additionOrder);
}
