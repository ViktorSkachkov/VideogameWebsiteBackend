package com.example.demo.business.cases.news;

import com.example.demo.domain.ValidationResponse;

public interface ValidateTitleUseCase {
    ValidationResponse validateTitle(String title);
}
