package com.example.demo.business.impl.additions;

import com.example.demo.business.cases.additions.DeleteAdditionUseCase;
import com.example.demo.domain.Addition;
import com.example.demo.domain.persistenceClasses.AdditionPersistence;
import com.example.demo.persistence.repositories.AdditionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeleteAdditionUseCaseImpl implements DeleteAdditionUseCase {
    private final AdditionRepository additionRepository;

    @Override
    public Addition DeleteAddition(int index) {
        additionRepository.deleteById(Long.valueOf(index));
        return null;
    }
}
