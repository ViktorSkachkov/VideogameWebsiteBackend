package com.example.demo.business.impl.users;

import com.example.demo.domain.User;
import com.example.demo.persistence.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetUserUseCaseImplTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private GetUserUseCaseImpl getUserUseCase;

    @Test
    void GetUser() {
        User expectedResult = User.builder()
                .id(1)
                .username("username1")
                .email("email1")
                .bankAccount("bankAccount1")
                .role("role1")
                .build();
        when(userRepository.GetUser(1))
                .thenReturn(expectedResult);
        User actualResult = getUserUseCase.GetUser(1);
        assertEquals(expectedResult, actualResult);
        verify(userRepository).GetUser(1);
    }
}