package com.example.demo.business.cases.addition;

import com.example.demo.domain.ValidationResponse;

public interface ValidateAdditionNameUseCase {
    ValidationResponse validateAdditionName(String name);
}
