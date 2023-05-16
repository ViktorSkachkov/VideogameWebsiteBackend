package com.example.demo.business.impl.addition;

import com.example.demo.business.cases.addition.UpdateAdditionUseCase;
import com.example.demo.domain.Addition;
import com.example.demo.persistence.entity.AdditionPersistence;
import com.example.demo.persistence.repository.AdditionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateAdditionUseCaseImpl implements UpdateAdditionUseCase {
    private final AdditionRepository additionRepository;

    /**
     * @param addition
     * @return
     */
    @Override
    public Addition updateAddition(Addition addition) {
        Optional<AdditionPersistence> ap = additionRepository.findById(Long.valueOf(addition.getId()));
        if (ap.isEmpty()) {

        }
        ap.get().setDescription(addition.getDescription());
        ap.get().setName(addition.getName());
        ap.get().setImage(addition.getImage());
        ap.get().setGame_id(addition.getGameId());
        ap.get().setPrice(addition.getPrice());
        additionRepository.save(ap.get());
        return addition;
    }
}
