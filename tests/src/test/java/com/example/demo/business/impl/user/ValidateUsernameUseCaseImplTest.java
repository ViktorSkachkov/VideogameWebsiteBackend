package com.example.demo.business.impl.user;

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
        when(userRepository.findAllUsernames(false))
                .thenReturn(List.of("Username"));
        boolean actualResult = validateUsernameUseCase.validateUsername("Username");
        assertEquals(true, actualResult);
    }
}