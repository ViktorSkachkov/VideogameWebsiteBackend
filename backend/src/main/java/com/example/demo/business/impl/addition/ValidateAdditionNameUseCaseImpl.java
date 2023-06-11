package com.example.demo.business.impl.addition;

import com.example.demo.business.cases.addition.ValidateAdditionNameUseCase;
import com.example.demo.domain.ValidationResponse;
import com.example.demo.persistence.repository.AdditionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ValidateAdditionNameUseCaseImpl implements ValidateAdditionNameUseCase {
    private final AdditionRepository additionRepository;

    @Override
    public ValidationResponse validateAdditionName(String name) {
        List<String> names = additionRepository.findAllNames(false);

        if(names.contains(name)) {
            return ValidationResponse.builder()
                    .confirm(true)
                    .build();
        }
        else {
            return ValidationResponse.builder()
                    .confirm(false)
                    .build();
        }
    }
}
