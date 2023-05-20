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
    private int reviewedItemId;
    private int numberOfTimesBought;
    private double price;
    private double totalPrice;
    private List<GameOrder> gameOrderList;
    private String name;

    public int calculateNumberOfTimesBought() {
        numberOfTimesBought = 0;
        for(GameOrder gop : gameOrderList) {
            numberOfTimesBought += gop.getUnits();
        }
        return numberOfTimesBought;
    }

    public double calculateTotalPrice() {
        totalPrice = 0;
            totalPrice = numberOfTimesBought * price;

        return totalPrice;
    }
}
