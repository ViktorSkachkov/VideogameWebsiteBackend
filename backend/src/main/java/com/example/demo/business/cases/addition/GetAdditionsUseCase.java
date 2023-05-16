package com.example.demo.business.cases.addition;

import com.example.demo.domain.Addition;

import java.util.List;

public interface GetAdditionsUseCase {
    List<Addition> getAdditions();

    List<Addition> reverseOrder(List<Addition> additions);
}


