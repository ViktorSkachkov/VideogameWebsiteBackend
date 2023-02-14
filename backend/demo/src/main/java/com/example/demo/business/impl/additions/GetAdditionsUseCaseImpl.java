package com.example.demo.business.impl.additions;

import com.example.demo.business.cases.additions.GetAdditionsUseCase;
import com.example.demo.domain.Addition;

import com.example.demo.persistence.repositories.AdditionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAdditionsUseCaseImpl implements GetAdditionsUseCase {
    private final AdditionRepository additionRepository;

    public GetAdditionsUseCaseImpl(AdditionRepository additionRepository) {
        this.additionRepository = additionRepository;
    }
    @Override
    public List<Addition> GetAdditions() {
        return additionRepository.GetAdditions();
    }
}
