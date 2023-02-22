package com.example.demo.persistence.repositories;

import com.example.demo.domain.Videogame;

import java.text.ParseException;
import java.util.List;

public interface VideogameRepository {
    List<Videogame> GetVideogames();
    List<Videogame> GetFeaturedVideogames();
    List<Videogame> GetUpcomingVideogames();
    Videogame GetVideogame(int index);
    Videogame AddVideogame(Videogame addition);
    Videogame UpdateVideogame(Videogame addition);
    Videogame DeleteVideogame(int index);
}
