package com.example.demo.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Entity
@Table(name = "news")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsPersistence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "game_id")
    @NotNull
    @EqualsAndHashCode.Exclude
    private int gameId;
    @NotBlank
    @Length(min = 2, max = 250)
    @Column(name = "title")
    private String title;
    @NotBlank
    @Length(min = 2, max = 1000)
    @Column(name = "text")
    private String text;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "image", length = 100000)
    private String image;
    @Column(name = "time")
    @NotNull
    @EqualsAndHashCode.Exclude
    private LocalDateTime time;
}
