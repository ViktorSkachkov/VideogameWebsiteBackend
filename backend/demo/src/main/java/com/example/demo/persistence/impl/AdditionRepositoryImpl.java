package com.example.demo.persistence.impl;

import com.example.demo.domain.Addition;
import com.example.demo.persistence.repositories.AdditionRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AdditionRepositoryImpl implements AdditionRepository {
    List<Addition> additions = new ArrayList<>();
    public AdditionRepositoryImpl() {
        additions.add(Addition.builder()
                        .id(1)
                        .gameId(1)
                        .name("name1")
                        .price(10)
                        .description("description1")
                        .image("image1")
                .build());
        additions.add(Addition.builder()
                .id(2)
                .gameId(1)
                .name("name2")
                .price(10)
                .description("description2")
                .image("image2")
                .build());
    }
    @Override
    public List<Addition> GetAdditions() {
        return additions;
    }

    @Override
    public Addition GetAddition(int index) {
        Addition returnAddition = Addition.builder().build();
        for(Addition a : additions) {
            if(a.getId() == index) {
                returnAddition = a;
            }
        }
        return returnAddition;
    }

    @Override
    public Addition AddAddition(Addition addition) {
        additions.add(addition);
        Addition returnAddition = Addition.builder().build();
        for(Addition a : additions) {
            if(a.getId() == addition.getId()) {
                returnAddition = a;
            }
        }
        return returnAddition;
    }

    @Override
    public Addition UpdateAddition(Addition addition) {
        Addition returnAddition = Addition.builder().build();

        int index = additions.indexOf(returnAddition);
        additions.remove(index);
        additions.add(index, addition);
        for(Addition a : additions) {
            if(a.getId() == addition.getId()) {
                returnAddition = a;
            }
        }
        return returnAddition;
    }

    @Override
    public Addition DeleteAddition(int index) {
        Addition returnAddition = Addition.builder().build();
        for(Addition a : additions) {
            if(a.getId() == index) {
                returnAddition = a;
            }
        }
        additions.remove(returnAddition);
        return returnAddition;
    }
}
