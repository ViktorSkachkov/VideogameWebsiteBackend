package com.example.demo.business.impl.videogames;

import com.example.demo.business.cases.videogames.UpdateVideogameUseCase;
import com.example.demo.domain.Videogame;
import com.example.demo.persistence.repositories.VideogameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateVideogameUseCaseImpl implements UpdateVideogameUseCase {
    private final VideogameRepository videogameRepository;

    @Override
    public Videogame UpdateVideogame(Videogame videogame) {
        return videogameRepository.UpdateVideogame(videogame);
    }
}
