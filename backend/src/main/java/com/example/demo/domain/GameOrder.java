package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@Builder
public class GameOrder {
    private int id;
    private int game;
    private int user;
    private int units;
    private LocalDateTime time;
    private String dateFormatted;
    private Boolean approved;
    private double totalPrice;
}
