package com.example.demo.business.cases.additionorder;

import com.example.demo.domain.RankingAdditionOrder;

import java.util.List;

public interface GetAdditionOrdersRankedUseCase {
    List<RankingAdditionOrder> getAdditionOrdersRanked(int id);

    List<RankingAdditionOrder> reverseOrder(List<RankingAdditionOrder> rankingAdditionOrders);
}
