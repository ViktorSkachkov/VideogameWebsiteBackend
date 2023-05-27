package com.example.demo.business.cases.gameorder;

import com.example.demo.domain.RankedClass;

import java.util.List;

public interface GetGameOrdersRankedUseCase {
    /*List<RankingGameOrder> getGameOrdersRanked(int id);

    List<RankingGameOrder> reverseOrder(List<RankingGameOrder> rankingGameOrders);*/
    public List<RankedClass> getGameOrdersRanked(int id);
}
