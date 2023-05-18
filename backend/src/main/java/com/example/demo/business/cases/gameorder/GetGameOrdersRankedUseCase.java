package com.example.demo.business.cases.gameorder;

import com.example.demo.domain.RankingAdditionOrder;
import com.example.demo.domain.RankingGameOrder;

import java.util.List;

public interface GetGameOrdersRankedUseCase {
    List<RankingGameOrder> getGameOrdersRanked(int id);

    List<RankingGameOrder> reverseOrder(List<RankingGameOrder> rankingGameOrders);
}
