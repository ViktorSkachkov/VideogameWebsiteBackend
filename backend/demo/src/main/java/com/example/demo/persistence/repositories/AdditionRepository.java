package com.example.demo.persistence.repositories;

import com.example.demo.domain.Addition;

import java.util.List;


public interface AdditionRepository {
    List<Addition> GetAdditions();
    Addition GetAddition(int index);
    Addition AddAddition(Addition addition);
    Addition UpdateAddition(Addition addition);
    Addition DeleteAddition(int index);
}
