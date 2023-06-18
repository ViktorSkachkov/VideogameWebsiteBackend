package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@Builder
public class Videogame {
    private int id;
    private String name;
    private double price;
    private String description;
    private Boolean featured;
    private String image;
    private LocalDateTime time;
    private Boolean deleted;
}
