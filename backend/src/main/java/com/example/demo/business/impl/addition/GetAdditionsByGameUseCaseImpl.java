package com.example.demo.business.impl.addition;

import com.example.demo.business.cases.addition.GetAdditionsByGameUseCase;
import com.example.demo.domain.Addition;
import com.example.demo.persistence.entity.AdditionPersistence;
import com.example.demo.persistence.repository.AdditionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import java.util.Collections;
import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class GetAdditionsByGameUseCaseImpl implements GetAdditionsByGameUseCase {
    private final AdditionRepository additionRepository;

    /**
     * @param index
     * @return
     */
    @Override
    public List<Addition> getAdditionsByGame(int index) {
        List<AdditionPersistence> list = additionRepository.findAll();
        List<Addition> additionsList = new ArrayList<>();
        list.forEach(ap ->
                {
                    if (index == -1 && !ap.getDeleted()) {
                        Addition addition = Addition.builder()
                                .id(Math.toIntExact(ap.getId()))
                                .image(ap.getImage())
                                .gameId(ap.getGameId())
                                .price(ap.getPrice())
                                .description(ap.getDescription())
                                .name(ap.getName())
                                .time(ap.getTime())
                                .deleted(ap.getDeleted())
                                .build();
                        additionsList.add(addition);
                    } else {
                        if (ap.getGameId() == index && !ap.getDeleted()) {
                            Addition addition = Addition.builder()
                                    .id(Math.toIntExact(ap.getId()))
                                    .image(ap.getImage())
                                    .gameId(ap.getGameId())
                                    .price(ap.getPrice())
                                    .description(ap.getDescription())
                                    .name(ap.getName())
                                    .time(ap.getTime())
                                    .deleted(ap.getDeleted())
                                    .build();
                            additionsList.add(addition);
                        }
                    }
                }
        );

        List<Addition> newList = reverseOrder(additionsList);
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
