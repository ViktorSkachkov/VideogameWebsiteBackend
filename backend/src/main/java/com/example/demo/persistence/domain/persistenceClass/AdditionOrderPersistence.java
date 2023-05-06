package com.example.demo.persistence.domain.persistenceClass;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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
    @NotNull
    @EqualsAndHashCode.Exclude
    private int addition;
    @Column(name = "user_id")
    @NotNull
    @EqualsAndHashCode.Exclude
    private int user;
    @Column(name = "units")
    @NotNull
    @EqualsAndHashCode.Exclude
    private int units;
}
