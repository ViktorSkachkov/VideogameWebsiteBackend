package com.example.demo.business.cases.additionorder;

import com.example.demo.domain.RankingAdditionOrder;

import java.util.List;

public interface GetAdditionOrdersRankedUseCase {
    List<RankingAdditionOrder> getAdditionOrdersRanked(/*LocalDateTime startDate, LocalDateTime endDate*/);

    List<RankingAdditionOrder> reverseOrder(List<RankingAdditionOrder> rankingAdditionOrders);
}
