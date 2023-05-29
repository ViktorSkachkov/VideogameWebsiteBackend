package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class RankedItem {
    private int itemId;
    private int units;
    private double totalIncome;
    private String name;
}
