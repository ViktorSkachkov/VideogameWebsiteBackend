package com.example.demo.business.impl.addition;

import com.example.demo.business.cases.addition.GetAdditionUseCase;
import com.example.demo.domain.Addition;
import com.example.demo.persistence.entity.AdditionPersistence;
import com.example.demo.persistence.repository.AdditionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetAdditionUseCaseImpl implements GetAdditionUseCase {
    private final AdditionRepository additionRepository;

    /**
     * @param id
     * @return
     */
    @Override
    public Addition getAddition(int id) {
        Optional<AdditionPersistence> ap = additionRepository.findById(Long.valueOf(id));
        if (ap.isEmpty()) {

        }
        Addition addition = Addition.builder()
                .id(Math.toIntExact(ap.get().getId()))
                .gameId(ap.get().getGame_id())
                .image(ap.get().getImage())
                .name(ap.get().getName())
                .description(ap.get().getDescription())
                .price(ap.get().getPrice())
                .time(ap.get().getTime())
                .deleted(ap.get().getDeleted())
                .build();
        return addition;
    }
}
