package com.example.demo.business.impl.videogames;

import com.example.demo.business.cases.videogames.GetVideogameUseCase;
import com.example.demo.domain.Videogame;
import com.example.demo.persistence.repositories.VideogameRepository;
import org.springframework.stereotype.Service;

@Service
public class GetVideogameUseCaseImpl implements GetVideogameUseCase {
    private final VideogameRepository videogameRepository;

    public GetVideogameUseCaseImpl(VideogameRepository videogameRepository) {
        this.videogameRepository = videogameRepository;
    }

    @Override
    public Videogame GetVideogame(int index) {
        return videogameRepository.GetVideogame(index);
    }
}
