package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class RankingAdditionOrder {
    private Long id;
    private int reviewed_item_id;
    private int numberOfTimesBought;
    private double price;
    private double totalPrice;
    private List<AdditionOrder> additionOrderList;
    private String name;

    public int calculateNumberOfTimesBought() {
        numberOfTimesBought = 0;
        for(AdditionOrder aop : additionOrderList) {
            numberOfTimesBought += aop.getUnits();
        }
        return numberOfTimesBought;
    }

    public double calculateTotalPrice() {
        totalPrice = 0;
        totalPrice = numberOfTimesBought * price;

        return totalPrice;
    }
}
