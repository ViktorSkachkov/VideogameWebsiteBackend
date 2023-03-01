package com.example.demo.business.impl.additions;

import com.example.demo.business.cases.additions.GetAdditionsUseCase;
import com.example.demo.domain.Addition;
import com.example.demo.domain.persistenceClasses.AdditionPersistence;
import com.example.demo.persistence.repositories.AdditionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAdditionsUseCaseImpl implements GetAdditionsUseCase {
    private final AdditionRepository additionRepository;

    @Override
    public List<Addition> GetAdditions() {
        List<AdditionPersistence> list = additionRepository.findAll();
        List<Addition> additions = new ArrayList<>();
        Addition addition;
        for(AdditionPersistence ap : list) {
            addition = Addition.builder()
                    .id(Math.toIntExact(ap.getId()))
                    .image(ap.getImage())
                    .name(ap.getName())
                    .description(ap.getDescription())
                    .price(ap.getPrice())
                    .build();
            additions.add(addition);
        }
        return additions;
    }
}
