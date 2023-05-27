package com.example.demo.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

/*@Entity
@Table(name = "ranking_addition_order")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor*/
public class RankingAdditionOrderPersistence {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "reviewed_item_id")
    @NotNull
    @EqualsAndHashCode.Exclude
    private int reviewedItemId;
    @Column(name = "number_of_times_bought")
    @NotNull
    @EqualsAndHashCode.Exclude
    private int numberOfTimesBought;
    @Column(name = "price")
    @NotNull
    @EqualsAndHashCode.Exclude
    private double price;
    @NotBlank
    @Length(min = 2, max = 100)
    @Column(name = "name")
    private String name;*/
}
