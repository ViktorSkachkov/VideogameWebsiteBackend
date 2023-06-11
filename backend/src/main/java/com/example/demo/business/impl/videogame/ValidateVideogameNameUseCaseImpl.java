package com.example.demo.business.impl.videogame;

import com.example.demo.business.cases.videogame.ValidateVideogameNameUseCase;
import com.example.demo.domain.ValidationResponse;
import com.example.demo.persistence.repository.VideogameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ValidateVideogameNameUseCaseImpl implements ValidateVideogameNameUseCase {
    private final VideogameRepository videogameRepository;

    @Override
    public ValidationResponse validateVideogameName(String name) {
        List<String> names = videogameRepository.findAllNames(false);

        if(names.contains(name)) {
            return ValidationResponse.builder()
                    .confirm(true)
                    .build();
        }
        else {
            return ValidationResponse.builder()
                    .confirm(false)
                    .build();
        }
    }
}
