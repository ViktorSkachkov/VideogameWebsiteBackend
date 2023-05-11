package com.example.demo.business.impl.user;

import com.example.demo.business.cases.AccessTokenEncoder;
import com.example.demo.domain.LoginResponse;
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

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateUserUseCaseImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private AccessTokenEncoder accessTokenEncoder;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UpdateUserUseCaseImpl updateUserUseCase;

    @Test
    void updateUser() {
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
                .username("username3")
                .email("email3")
                .bankAccount("bankAccount3")
                .userRoles(Set.of(role))
                .build();
        UserPersistence user = UserPersistence.builder()
                .id(1L)
                .username("username3")
                .email("email3")
                .bank_account("bankAccount3")
                .userRoles(Set.of(rp))
                .build();

        when(userRepository.findById(1L))
                .thenReturn(Optional.ofNullable(user));
        userRepository.findById(Long.valueOf(user.getId()));

        when(userRepository.save(user))
                .thenReturn(user);
        LoginResponse actualResult = updateUserUseCase.updateUser(expectedResult);

        //assertEquals(expectedResult, actualResult);
        verify(userRepository).save(user);
    }
}