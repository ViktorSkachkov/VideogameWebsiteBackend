package com.example.demo.business.cases.addition;

import com.example.demo.domain.Addition;

import java.util.List;

public interface GetAdditionsByGameUseCase {
    List<Addition> getAdditionsByGame(int index);
}
