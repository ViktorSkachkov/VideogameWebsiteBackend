package com.example.demo.business.impl.videogames;

import com.example.demo.business.cases.videogames.DeleteVideogameUseCase;
import com.example.demo.domain.Videogame;
import com.example.demo.persistence.repositories.VideogameRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteVideogameUseCaseImpl implements DeleteVideogameUseCase {
    private final VideogameRepository videogameRepository;

    public DeleteVideogameUseCaseImpl(VideogameRepository videogameRepository) {
        this.videogameRepository = videogameRepository;
    }

    @Override
    public Videogame DeleteVideogame(int index) {
        return videogameRepository.DeleteVideogame(index);
    }
}
