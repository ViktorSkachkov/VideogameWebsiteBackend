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
     * @return
     */
    @Override
    public List<Addition> getAdditions() {
        List<AdditionPersistence> list = additionRepository.findAll();
        List<Addition> additions = new ArrayList<>();
        list.forEach(ap ->
                {
                    if(!ap.getDeleted()) {
                        Addition addition = Addition.builder()
                                .id(Math.toIntExact(ap.getId()))
                                .gameId(ap.getGameId())
                                .image(ap.getImage())
                                .name(ap.getName())
                                .description(ap.getDescription())
                                .price(ap.getPrice())
                                .time(ap.getTime())
                                .deleted(ap.getDeleted())
                                .build();
                        additions.add(addition);
                    }
                }
        );

        List<Addition> newList = reverseOrder(additions);
        return newList;
    }

    @Override
    public List<Addition> reverseOrder(List<Addition> additions) {
        List<Addition> result = new ArrayList<>();

        for (int i = additions.size() - 1; i >= 0; i--) {
            result.add(additions.get(i));
        }

        return result;
    }
}
