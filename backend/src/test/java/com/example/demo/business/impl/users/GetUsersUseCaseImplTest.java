package com.example.demo.business.impl.users;

import com.example.demo.domain.User;
import com.example.demo.domain.persistenceClasses.UserPersistence;
import com.example.demo.persistence.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetUsersUseCaseImplTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private GetUsersUseCaseImpl getUsersUseCase;

    @Test
    void GetUsers() {
        User user1 = User.builder()
                .id(1)
                .username("username1")
                .email("email1")
                .bankAccount("bankAccount1")
                .role("role1")
                .build();
        User user2 = User.builder()
                .id(2)
                .username("username2")
                .email("email2")
                .bankAccount("bankAccount2")
                .role("role2")
                .build();
        UserPersistence userPersistence1 = UserPersistence.builder()
                .id(1L)
                .username("username1")
                .email("email1")
                .bank_account("bankAccount1")
                .role("role1")
                .build();
        UserPersistence userPersistence2 = UserPersistence.builder()
                .id(2L)
                .username("username2")
                .email("email2")
                .bank_account("bankAccount2")
                .role("role2")
                .build();
        when(userRepository.findAll())
                .thenReturn(List.of(userPersistence1, userPersistence2));
        List<User> actualResult = getUsersUseCase.GetUsers();
        List<User> expectedResult = new ArrayList<>();
        expectedResult.add(user1);
        expectedResult.add(user2);
        assertEquals(expectedResult, actualResult);
        verify(userRepository).findAll();
    }
}