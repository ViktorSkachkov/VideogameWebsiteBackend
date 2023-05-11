package com.example.demo.business.impl.addition;

import com.example.demo.business.cases.addition.AddAdditionUseCase;
import com.example.demo.domain.Addition;
import com.example.demo.persistence.domain.persistenceClass.AdditionPersistence;
import com.example.demo.persistence.repository.AdditionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddAdditionUseCaseImpl implements AddAdditionUseCase {
    private final AdditionRepository additionRepository;

    /**
     *
     * @param addition
     * @return
     */
    @Override
    public Addition addAddition(Addition addition) {
        if(addition.getDescription().length() <= 500) {
            AdditionPersistence ap = AdditionPersistence.builder()
                    .description(addition.getDescription())
                    .name(addition.getName())
                    .image(addition.getImage())
                    .game_id(addition.getGameId())
                    .price(addition.getPrice())
                    .build();
            additionRepository.save(ap);
        }
        else {

        }
        return addition;
    }
}
