package com.example.demo.domain;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@Data
@Builder
public class RankedItem {
    private int itemId;
    private double units;
    private double totalIncome;
    private String name;
}
