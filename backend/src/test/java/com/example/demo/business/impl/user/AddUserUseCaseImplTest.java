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
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@ExtendWith(MockitoExtension.class)
class AddUserUseCaseImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private AddUserUseCaseImpl addUserUseCase;

    @Test
    void addUser() {
        String encodedPassword = passwordEncoder.encode("password");

        User expectedResult = User.builder()
                .id(3)
                .username("username3")
                .email("email3")
                .pwd(encodedPassword)
                .bankAccount("bankAccount3")
                .userRoles(Set.of(Role.builder()
                        .id(1)
                        .role("EMPLOYEE")
                        .user_id(3)
                        .build()))
                .deleted(false)
                .build();

        UserPersistence user = UserPersistence.builder()
                .username("username3")
                .email("email3")
                .bank_account("bankAccount3")
                .pwd(encodedPassword)
                .userRoles(Set.of(RolePersistence.builder()
                        .id(1L)
                        .role("EMPLOYEE")
                        .user(3L)
                        .build()))
                .deleted(false)
                .build();

        /*when(userRepository.save(user))
                .thenReturn(user);
        User actualResult = addUserUseCase.AddUser(expectedResult);
        assertEquals(expectedResult, actualResult);
        verify(userRepository).save(user);*/
    }
}