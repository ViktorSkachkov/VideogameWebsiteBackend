package com.example.demo.business.cases.videogame;

import com.example.demo.domain.Videogame;

import java.util.List;

public interface GetVideogamesForNewsFilterUseCase {
    List<Videogame> getVideogamesorNewsFilter();
}
