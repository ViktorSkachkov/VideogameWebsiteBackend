package com.example.demo.business.impl.videogame;

import com.example.demo.business.cases.videogame.ValidateVideogameNameUseCase;
import com.example.demo.persistence.repository.VideogameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ValidateVideogameNameUseCaseImpl implements ValidateVideogameNameUseCase {
    private final VideogameRepository videogameRepository;

    @Override
    public boolean validateVideogameName(String name) {
        List<String> names = videogameRepository.findAllNames(false);

        if(names.contains(name)) {
            return true;
        }
        else {
            return false;
        }
    }
}
