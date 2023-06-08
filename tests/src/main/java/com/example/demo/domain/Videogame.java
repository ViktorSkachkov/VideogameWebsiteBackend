package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
