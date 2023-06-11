package com.example.demo.business.impl.user;

import com.example.demo.domain.ValidationResponse;
import com.example.demo.persistence.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidatePasswordUseCaseImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private ValidatePasswordUseCaseImpl validatePasswordUseCase;

    @Test
    void validateFalsePassword() {
        ValidationResponse expectedResponse = ValidationResponse.builder()
                .confirm(false)
                .build();
        when(userRepository.findAllPasswords(false))
                .thenReturn(List.of("password"));
        ValidationResponse actualResult = validatePasswordUseCase.validatePassword("password1");
        assertEquals(expectedResponse, actualResult);
    }
}