package com.example.demo.business.impl.additions;

import com.example.demo.business.cases.additions.GetAdditionsUseCase;

import com.example.demo.domain.Addition;
import com.example.demo.persistence.repositories.AdditionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAdditionsUseCaseImpl implements GetAdditionsUseCase {
    private final AdditionRepository additionRepository;

    @Override
    public List<Addition> GetAdditions() {
        return additionRepository.GetAdditions();
    }
}
