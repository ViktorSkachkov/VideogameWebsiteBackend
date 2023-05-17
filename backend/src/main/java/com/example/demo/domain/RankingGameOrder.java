package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class RankingGameOrder {
    private Long id;
    private int reviewed_item_id;
    private int numberOfTimesBought;
    private double totalPrice;
    private List<GameOrder> gameOrderList;

    public int calculateNumberOfTimesBought() {
        numberOfTimesBought = 0;
        for(GameOrder gop : gameOrderList) {
            numberOfTimesBought += gop.getUnits();
        }
        return numberOfTimesBought;
    }
}
