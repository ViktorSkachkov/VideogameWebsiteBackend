package com.example.demo.business.cases.videogames;

import com.example.demo.domain.Videogame;

import java.text.ParseException;
import java.util.List;

public interface GetUpcomingVideogamesUseCase {
    List<Videogame> GetUpcomingVideogames();
}
