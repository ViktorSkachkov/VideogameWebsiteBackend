package com.example.demo.business.impl.videogames;

import com.example.demo.business.cases.videogames.AddVideogameUseCase;
import com.example.demo.domain.Videogame;
import com.example.demo.persistence.repositories.VideogameRepository;
import org.springframework.stereotype.Service;

@Service
public class AddVideogameUseCaseImpl implements AddVideogameUseCase {
    private final VideogameRepository videogameRepository;

    public AddVideogameUseCaseImpl(VideogameRepository videogameRepository) {
        this.videogameRepository = videogameRepository;
    }

    @Override
    public Videogame AddVideogame(Videogame videogame) {
        return videogameRepository.AddVideogame(videogame);
    }
}
