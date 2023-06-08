package com.example.demo.business.cases.gameorder;

import com.example.demo.domain.RankedItem;

import java.time.LocalDateTime;
import java.util.List;

public interface GetGameOrdersRankedUseCase {
    public List<RankedItem> getGameOrdersRanked(int id,  LocalDateTime endDate);
}
