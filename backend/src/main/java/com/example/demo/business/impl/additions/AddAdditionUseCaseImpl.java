package com.example.demo.business.impl.additions;

import com.example.demo.business.cases.additions.AddAdditionUseCase;
import com.example.demo.domain.Addition;
import com.example.demo.persistence.repositories.AdditionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddAdditionUseCaseImpl implements AddAdditionUseCase {
    private final AdditionRepository additionRepository;

    @Override
    public Addition AddAddition(Addition addition) {
        return additionRepository.AddAddition(addition);
    }
}
