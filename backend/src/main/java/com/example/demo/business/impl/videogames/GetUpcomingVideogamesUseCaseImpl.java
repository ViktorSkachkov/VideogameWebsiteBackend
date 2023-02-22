package com.example.demo.business.impl.videogames;

import com.example.demo.business.cases.videogames.GetUpcomingVideogamesUseCase;
import com.example.demo.domain.Videogame;
import com.example.demo.persistence.repositories.VideogameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetUpcomingVideogamesUseCaseImpl implements GetUpcomingVideogamesUseCase {
    private final VideogameRepository videogameRepository;

    @Override
    public List<Videogame> GetUpcomingVideogames() {
        return videogameRepository.GetUpcomingVideogames();
    }
}
