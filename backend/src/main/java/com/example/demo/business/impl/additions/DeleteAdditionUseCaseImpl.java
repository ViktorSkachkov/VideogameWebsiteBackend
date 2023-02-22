package com.example.demo.business.impl.additions;

import com.example.demo.business.cases.additions.DeleteAdditionUseCase;
import com.example.demo.domain.Addition;
import com.example.demo.persistence.repositories.AdditionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteAdditionUseCaseImpl implements DeleteAdditionUseCase {
    private final AdditionRepository additionRepository;

    @Override
    public Addition DeleteAddition(int index) {
        return additionRepository.DeleteAddition(index);
    }
}
