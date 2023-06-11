package com.example.demo.business.cases.videogame;

import com.example.demo.domain.ValidationResponse;

public interface ValidateVideogameNameUseCase {
    ValidationResponse validateVideogameName(String name);
}
