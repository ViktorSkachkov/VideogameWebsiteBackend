package com.example.demo.business.impl.additions;

import com.example.demo.business.cases.additions.GetAdditionUseCase;
import com.example.demo.domain.Addition;
import com.example.demo.persistence.repositories.AdditionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAdditionUseCaseImpl implements GetAdditionUseCase {
    private final AdditionRepository additionRepository;

    @Override
    public Addition GetAddition(int index) {
        return additionRepository.GetAddition(index);
    }
}
