package com.example.demo.business.impl.additions;

import com.example.demo.business.cases.additions.DeleteAdditionUseCase;
import com.example.demo.domain.Addition;
import com.example.demo.persistence.repositories.AdditionRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteAdditionUseCaseImpl implements DeleteAdditionUseCase {
    private final AdditionRepository additionRepository;

    public DeleteAdditionUseCaseImpl(AdditionRepository additionRepository) {
        this.additionRepository = additionRepository;
    }

    @Override
    public Addition DeleteAddition(int index) {
        return additionRepository.DeleteAddition(index);
    }
}
