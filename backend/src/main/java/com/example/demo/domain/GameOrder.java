package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
}
