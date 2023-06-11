package com.example.demo.business.cases.user;

import com.example.demo.domain.ValidationResponse;

public interface ValidatePasswordUseCase {
    ValidationResponse validatePassword(String password);
}
