package com.example.demo.persistence.impl;

import com.example.demo.domain.Addition;
import com.example.demo.domain.Videogame;
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
                        .description("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?")
                        .image("image1")
                .build());
        additions.add(Addition.builder()
                .id(2)
                .gameId(1)
                .name("name2")
                .price(10)
                .description("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?")
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
        for(Addition a : additions) {
            if(a.getId() == addition.getId()) {
                returnAddition = a;
            }
        }
        int index = additions.indexOf(returnAddition);
        additions.remove(index);
        additions.add(index, addition);
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
