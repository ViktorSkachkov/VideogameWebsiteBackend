package com.example.demo.business.cases.videogame;

import com.example.demo.domain.Videogame;

import java.util.List;

public interface GetVideogamesUseCase {
    List<Videogame> getVideogames();

    List<Videogame> reverseOrder(List<Videogame> videogames);
}
