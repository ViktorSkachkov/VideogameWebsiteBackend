package com.example.demo.business.impl.videogames;

import com.example.demo.business.cases.videogames.AddVideogameUseCase;
import com.example.demo.domain.Videogame;
import com.example.demo.persistence.repositories.VideogameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddVideogameUseCaseImpl implements AddVideogameUseCase {
    private final VideogameRepository videogameRepository;

    @Override
    public Videogame AddVideogame(Videogame videogame) {
        return videogameRepository.AddVideogame(videogame);
    }
}
