package com.example.demo.business.cases.additionorder;

import com.example.demo.domain.RankedItem;

import java.time.LocalDateTime;
import java.util.List;

public interface GetAdditionOrdersRankedUseCase {
    List<RankedItem> getAdditionOrdersRanked(int id,  LocalDateTime endDate);
}
