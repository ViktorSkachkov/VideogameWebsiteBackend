package com.example.demo.business.impl.videogames;

import com.example.demo.business.cases.videogames.GetFeaturedVideogamesUseCase;
import com.example.demo.domain.Videogame;
import com.example.demo.persistence.repositories.VideogameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetFeaturedVideogamesUseCaseImpl implements GetFeaturedVideogamesUseCase {
    private final VideogameRepository videogameRepository;

    @Override
    public List<Videogame> GetFeaturedVideogames() {
        return videogameRepository.GetFeaturedVideogames();
    }
}
