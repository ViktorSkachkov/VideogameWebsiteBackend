package com.example.demo.business.impl.videogames;

import com.example.demo.business.cases.videogames.GetVideogamesUseCase;
import com.example.demo.domain.Videogame;
import com.example.demo.persistence.repositories.VideogameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetVideogamesUseCaseImpl implements GetVideogamesUseCase {
    private final VideogameRepository videogameRepository;

    public GetVideogamesUseCaseImpl(VideogameRepository videogameRepository) {
        this.videogameRepository = videogameRepository;
    }

    @Override
    public List<Videogame> GetVideogames() {
        return videogameRepository.GetVideogames();
    }
}
