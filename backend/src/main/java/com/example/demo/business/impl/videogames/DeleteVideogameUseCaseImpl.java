package com.example.demo.business.impl.videogames;

import com.example.demo.business.cases.videogames.DeleteVideogameUseCase;
import com.example.demo.domain.Videogame;
import com.example.demo.persistence.repositories.VideogameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteVideogameUseCaseImpl implements DeleteVideogameUseCase {
    private final VideogameRepository videogameRepository;

    @Override
    public Videogame DeleteVideogame(int index) {
        return videogameRepository.DeleteVideogame(index);
    }
}
