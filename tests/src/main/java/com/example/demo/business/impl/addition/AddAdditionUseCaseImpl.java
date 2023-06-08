package com.example.demo.business.impl.addition;

import com.example.demo.business.cases.addition.AddAdditionUseCase;
import com.example.demo.domain.Addition;
import com.example.demo.persistence.entity.AdditionPersistence;
import com.example.demo.persistence.repository.AdditionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AddAdditionUseCaseImpl implements AddAdditionUseCase {
    private final AdditionRepository additionRepository;

    /**
     * @param addition
     * @return
     */
    @Override
    public Addition addAddition(Addition addition) {
        if (addition.getDescription().length() <= 500) {
            AdditionPersistence ap = AdditionPersistence.builder()
                    .description(addition.getDescription())
                    .name(addition.getName())
                    .image(addition.getImage())
                    .gameId(addition.getGameId())
                    .price(addition.getPrice())
                    .time(LocalDateTime.now())
                    .deleted(false)
                    .build();
            additionRepository.save(ap);
        } else {

        }
        return addition;
    }
}
