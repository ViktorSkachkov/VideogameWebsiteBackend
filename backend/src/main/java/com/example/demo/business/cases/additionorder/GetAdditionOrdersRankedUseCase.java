package com.example.demo.business.cases.additionorder;

import com.example.demo.domain.AdditionOrder;
import com.example.demo.domain.RankedClass;
import com.example.demo.domain.RankingAdditionOrder;
import com.example.demo.persistence.entity.AdditionOrderPersistence;

import java.util.List;

public interface GetAdditionOrdersRankedUseCase {
    /*List<RankingAdditionOrder> getAdditionOrdersRanked(int id);

    List<RankingAdditionOrder> reverseOrder(List<RankingAdditionOrder> rankingAdditionOrders);*/
    List<RankedClass> getAdditionOrdersRanked(int id);
}
