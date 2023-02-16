package com.example.demo.business.impl.additions;

import com.example.demo.business.cases.additions.UpdateAdditionUseCase;
import com.example.demo.domain.Addition;
import com.example.demo.persistence.repositories.AdditionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateAdditionUseCaseImpl implements UpdateAdditionUseCase {
    private final AdditionRepository additionRepository;

    @Override
    public Addition UpdateAddition(Addition addition) {
        return additionRepository.UpdateAddition(addition);
    }
}
