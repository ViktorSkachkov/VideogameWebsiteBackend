package com.example.demo.domain;

import lombok.*;

@AllArgsConstructor
@Data
@Builder
public class Videogame {
    private int id;
    private String name;
    private double price;
    private String description;
    private String image;
}
