package com.example.demo.business.impl.additions;

import com.example.demo.business.cases.additions.UpdateAdditionUseCase;
import com.example.demo.domain.Addition;
import com.example.demo.persistence.repositories.AdditionRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateAdditionUseCaseImpl implements UpdateAdditionUseCase {
    private final AdditionRepository additionRepository;

    public UpdateAdditionUseCaseImpl(AdditionRepository additionRepository) {
        this.additionRepository = additionRepository;
    }

    @Override
    public Addition UpdateAddition(Addition addition) {
        return additionRepository.UpdateAddition(addition);
    }
}
