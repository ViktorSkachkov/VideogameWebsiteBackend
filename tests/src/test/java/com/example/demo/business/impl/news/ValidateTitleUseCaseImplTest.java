package com.example.demo.business.impl.news;

import com.example.demo.domain.ValidationResponse;
import com.example.demo.persistence.repository.NewsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidateTitleUseCaseImplTest {
    @Mock
    private NewsRepository newsRepository;
    @InjectMocks
    private ValidateTitleUseCaseImpl validateTitleUseCase;

    @Test
    void validateTitle() {
        ValidationResponse expectedResponse = ValidationResponse.builder()
                .confirm(true)
                .build();
        when(newsRepository.findAllTitles())
                .thenReturn(List.of("Name"));
        ValidationResponse actualResult = validateTitleUseCase.validateTitle("Name");
        assertEquals(expectedResponse, actualResult);
    }

    @Test
    void validateFalseTitle() {
        ValidationResponse expectedResponse = ValidationResponse.builder()
                .confirm(false)
                .build();
        when(newsRepository.findAllTitles())
                .thenReturn(List.of("Name"));
        ValidationResponse actualResult = validateTitleUseCase.validateTitle("Name1");
        assertEquals(expectedResponse, actualResult);
    }
}