package com.example.demo.business.cases.videogame;

import com.example.demo.domain.Videogame;

public interface GetVideogameUseCase {
    Videogame GetVideogame(int index);
}
