package com.example.demo.business.impl.user;

import com.example.demo.domain.ValidationResponse;
import com.example.demo.persistence.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidateUsernameUseCaseImplTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private ValidateUsernameUseCaseImpl validateUsernameUseCase;

    @Test
    void validateUsername() {
        ValidationResponse expectedResponse = ValidationResponse.builder()
                .confirm(true)
                .build();
        when(userRepository.findAllUsernames(false))
                .thenReturn(List.of("Username"));
        ValidationResponse actualResult = validateUsernameUseCase.validateUsername("Username");
        assertEquals(expectedResponse, actualResult);
    }

    @Test
    void validateFalseUsername() {
        ValidationResponse expectedResponse = ValidationResponse.builder()
                .confirm(false)
                .build();
        when(userRepository.findAllUsernames(false))
                .thenReturn(List.of("Username"));
        ValidationResponse actualResult = validateUsernameUseCase.validateUsername("Username1");
        assertEquals(expectedResponse, actualResult);
    }
}