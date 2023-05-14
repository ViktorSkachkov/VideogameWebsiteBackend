package com.example.demo.business.impl.addition;

import com.example.demo.business.cases.addition.GetAdditionsUseCase;
import com.example.demo.domain.Addition;
import com.example.demo.persistence.entity.AdditionPersistence;
import com.example.demo.persistence.repository.AdditionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAdditionsUseCaseImpl implements GetAdditionsUseCase {
    private final AdditionRepository additionRepository;

    /**
     *
     * @return
     */
    @Override
    public List<Addition> getAdditions() {
        List<AdditionPersistence> list = additionRepository.findAll();
        List<Addition> additions = new ArrayList<>();
        for(AdditionPersistence ap : list) {
            Addition addition = Addition.builder()
                    .id(Math.toIntExact(ap.getId()))
                    .gameId(ap.getGame_id())
                    .image(ap.getImage())
                    .name(ap.getName())
                    .description(ap.getDescription())
                    .price(ap.getPrice())
                    .time(ap.getTime())
                    .build();
            additions.add(addition);
        }

        additions.sort(Comparator.comparing(addition -> addition.getTime(), Collections.reverseOrder()));

        return additions;
    }
}
