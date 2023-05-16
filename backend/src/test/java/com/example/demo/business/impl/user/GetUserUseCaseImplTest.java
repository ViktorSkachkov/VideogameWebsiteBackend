package com.example.demo.business.impl.user;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.persistence.entity.RolePersistence;
import com.example.demo.persistence.entity.UserPersistence;
import com.example.demo.persistence.repository.RoleRepository;
import com.example.demo.persistence.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetUserUseCaseImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @InjectMocks
    private GetUserUseCaseImpl getUserUseCase;

    @Test
    void getUser() {
        RolePersistence rp = RolePersistence.builder()
                .id(1L)
                .role("CUSTOMER")
                .user(1L)
                .build();
        Role role = Role.builder()
                .id(1)
                .user_id(1)
                .role("CUSTOMER")
                .build();
        User expectedResult = User.builder()
                .id(1)
                .username("username1")
                .email("email1")
                .bankAccount("bankAccount1")
                .userRoles(Set.of(role))
                .deleted(false)
                .build();
        UserPersistence user = UserPersistence.builder()
                .id(1L)
                .username("username1")
                .email("email1")
                .bank_account("bankAccount1")
                .userRoles(Set.of(rp))
                .deleted(false)
                .build();
        when(userRepository.findById(1L))
                .thenReturn(Optional.ofNullable(user));
        User actualResult = getUserUseCase.getUser(1);
        assertEquals(expectedResult, actualResult);
        verify(userRepository).findById(1L);
    }
}