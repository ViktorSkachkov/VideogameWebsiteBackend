package com.example.demo.persistence.impl;

import com.example.demo.domain.Videogame;
import com.example.demo.persistence.repositories.VideogameRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class VideogameRepositoryImpl implements VideogameRepository {
    List<Videogame> videogames = new ArrayList<>();
    public VideogameRepositoryImpl() {
        videogames.add(Videogame.builder()
                        .id(1)
                        .name("name1")
                        .price(10)
                        .description("description1")
                        .image("image1")
                .build());
        videogames.add(Videogame.builder()
                .id(2)
                .name("name2")
                .price(10)
                .description("description2")
                .image("image2")
                .build());
    }
    @Override
    public List<Videogame> GetVideogames() {
        return videogames;
    }

    @Override
    public Videogame GetVideogame(int index) {
        Videogame returnVideogame = Videogame.builder().build();
        for(Videogame v : videogames) {
            if(v.getId() == index) {
                returnVideogame = v;
            }
        }
        return returnVideogame;
    }

    @Override
    public Videogame AddVideogame(Videogame addition) {
        videogames.add(addition);
        Videogame returnVideogame = Videogame.builder().build();
        for(Videogame v : videogames) {
            if(v.getId() == addition.getId()) {
                returnVideogame = v;
            }
        }
        return returnVideogame;
    }

    @Override
    public Videogame UpdateVideogame(Videogame addition) {
        Videogame returnVideogame = Videogame.builder().build();
        for(Videogame v : videogames) {
            if(v.getId() == addition.getId()) {
                returnVideogame = v;
            }
        }
        int index = videogames.indexOf(returnVideogame);
        videogames.remove(index);
        videogames.add(index, addition);
        return returnVideogame;
    }

    @Override
    public Videogame DeleteVideogame(int index) {
        Videogame returnVideogame = Videogame.builder().build();
        for(Videogame v : videogames) {
            if(v.getId() == index) {
                returnVideogame = v;
            }
        }
        videogames.remove(returnVideogame);
        return returnVideogame;
    }
}
