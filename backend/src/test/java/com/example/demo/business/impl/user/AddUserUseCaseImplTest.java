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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

        Role role = Role.builder()
                .role("EMPLOYEE")
                .build();

        User expectedResult = User.builder()
                .username("username3")
                .email("email3")
                .pwd(encodedPassword)
                .bankAccount("bankAccount3")
                .userRoles(Set.of(role))
                .deleted(false)
                .build();

        RolePersistence rolePersistence = RolePersistence.builder()
                .role("EMPLOYEE")
                .build();

        UserPersistence user = UserPersistence.builder()
                .username("username3")
                .email("email3")
                .bankAccount("bankAccount3")
                .pwd(encodedPassword)
                .deleted(false)
                .build();

        when(userRepository.save(user))
                .thenReturn(user);
        when(roleRepository.save(rolePersistence))
                .thenReturn(rolePersistence);
        User actualResult = addUserUseCase.addUser(expectedResult);
        assertEquals(expectedResult, actualResult);
        verify(userRepository).save(user);
        verify(roleRepository).save(rolePersistence);
    }
}