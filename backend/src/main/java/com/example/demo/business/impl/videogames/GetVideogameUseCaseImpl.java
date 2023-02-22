package com.example.demo.business.impl.videogames;

import com.example.demo.business.cases.videogames.GetVideogameUseCase;
import com.example.demo.domain.Videogame;
import com.example.demo.persistence.repositories.VideogameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetVideogameUseCaseImpl implements GetVideogameUseCase {
    private final VideogameRepository videogameRepository;

    @Override
    public Videogame GetVideogame(int index) {
        return videogameRepository.GetVideogame(index);
    }
}
