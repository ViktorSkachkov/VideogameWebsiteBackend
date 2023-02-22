package com.example.demo.domain;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@Data
@Builder
public class Addition {
    @NotNull
    @EqualsAndHashCode.Exclude
    private int id;
    @NotNull
    @EqualsAndHashCode.Exclude
    private int gameId;
    @NotNull
    private String name;
    @NotNull
    @EqualsAndHashCode.Exclude
    private double price;
    @NotNull
    private String description;
    @NotNull
    private String image;
}
