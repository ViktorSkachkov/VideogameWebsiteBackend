package com.example.demo.business.cases.videogame;

import com.example.demo.domain.Videogame;

import java.util.List;

public interface GetUpcomingVideogamesUseCase {
    List<Videogame> getUpcomingVideogames();
}
