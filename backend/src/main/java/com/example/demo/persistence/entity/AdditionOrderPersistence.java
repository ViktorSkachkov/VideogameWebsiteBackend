package com.example.demo.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "addition_order")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdditionOrderPersistence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "addition_id")

    @EqualsAndHashCode.Exclude
    private int addition;
    @Column(name = "user_id")
    @NotNull
    @EqualsAndHashCode.Exclude
    private int user;
    @Column(name = "units")

    @EqualsAndHashCode.Exclude
    private int units;
    @Column(name = "time")
    @NotNull
    @EqualsAndHashCode.Exclude
    private LocalDateTime time;
    @Column(name = "approved")
    @NotNull
    @EqualsAndHashCode.Exclude
    private Boolean approved;
    @Column(name = "total_price")

    @EqualsAndHashCode.Exclude
    private double totalPrice;
}
