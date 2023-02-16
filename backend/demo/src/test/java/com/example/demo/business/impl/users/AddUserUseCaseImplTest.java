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
class AddUserUseCaseImplTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private AddUserUseCaseImpl addUserUseCase;

    @Test
    void AddUser() {
        User expectedResult = User.builder()
                .id(3)
                .username("username3")
                .email("email3")
                .bankAccount("bankAccount3")
                .role("role3")
                .build();
        when(userRepository.AddUser(expectedResult))
                .thenReturn(expectedResult);
        User actualResult = userRepository.AddUser(expectedResult);
        assertEquals(expectedResult, actualResult);
        verify(userRepository).AddUser(expectedResult);
    }
}