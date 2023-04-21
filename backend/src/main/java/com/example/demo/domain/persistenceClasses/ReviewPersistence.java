package com.example.demo.domain.persistenceClasses;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Entity
@Table(name = "review")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewPersistence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "reviewed_item_id")
    @NotNull
    @EqualsAndHashCode.Exclude
    private int reviewed_item_id;
    @Column(name = "user_id")
    @NotNull
    @EqualsAndHashCode.Exclude
    private int user_id;
    @NotBlank
    @Length(min = 2 ,max = 1000)
    @Column(name = "text")
    private String text;
    @Column(name = "time")
    @NotNull
    @EqualsAndHashCode.Exclude
    private LocalDateTime time;
    @NotBlank
    @Length(min = 2 ,max = 45)
    @Column(name = "type_of_reviewed_item")
    private String type_of_reviewed_item;
}
