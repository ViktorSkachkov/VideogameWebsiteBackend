package com.example.demo.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "game_order")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameOrderPersistence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "game_id")
    @NotNull
    @EqualsAndHashCode.Exclude
    private int game;
    @Column(name = "user_id")
    @NotNull
    @EqualsAndHashCode.Exclude
    private int user;
    @Column(name = "units")
    @NotNull
    @EqualsAndHashCode.Exclude
    private int units;
}