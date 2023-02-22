package com.example.demo.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@Data
@Builder
public class Videogame {
    private int id;
    private String name;
    private double price;
    private String description;
    //private LocalDateTime releaseDate;
    private Boolean featured;
    private String image;
}
