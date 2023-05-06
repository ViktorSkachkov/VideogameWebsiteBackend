package com.example.demo.business.cases.videogame;

import com.example.demo.domain.Videogame;

public interface DeleteVideogameUseCase {
    Videogame DeleteVideogame(int index);
}
