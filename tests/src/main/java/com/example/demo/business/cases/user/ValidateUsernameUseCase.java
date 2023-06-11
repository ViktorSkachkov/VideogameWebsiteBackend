package com.example.demo.business.cases.user;

import com.example.demo.domain.ValidationResponse;

public interface ValidateUsernameUseCase {
    ValidationResponse validateUsername(String username);
}
