package com.example.demo.business.impl.user;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.persistence.entity.RolePersistence;
import com.example.demo.persistence.entity.UserPersistence;
import com.example.demo.persistence.repository.AdditionOrderRepository;
import com.example.demo.persistence.repository.GameOrderRepository;
import com.example.demo.persistence.repository.RoleRepository;
import com.example.demo.persistence.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteUserUseCaseImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private AdditionOrderRepository additionOrderRepository;
    @Mock
    private GameOrderRepository gameOrderRepository;
    @InjectMocks
    private DeleteUserUseCaseImpl deleteUserUseCase;

    @Test
    void deleteUser() {
        Set<Role> rolesSet = new HashSet<>();
        Role role = Role.builder()
                .id(1)
                .role("EMPLOYEE")
                .userId(1)
                .build();
        rolesSet.add(role);
        User expectedResult = User.builder()
                .id(1)
                .username("username1")
                .pwd("password")
                .email("email1")
                .bankAccount("bankAccount1")
                .userRoles(rolesSet)
                .deleted(true)
                .build();

        Set<RolePersistence> rolePersistenceSet = new HashSet<>();
        RolePersistence rolePersistence = RolePersistence.builder()
                .id(1L)
                .role("EMPLOYEE")
                .user(1L)
                .build();
        rolePersistenceSet.add(rolePersistence);
        UserPersistence user = UserPersistence.builder()
                .id(1L)
                .username("username1")
                .pwd("password")
                .email("email1")
                .bankAccount("bankAccount1")
                .userRoles(rolePersistenceSet)
                .deleted(true)
                .build();

        when(userRepository.findById(1L))
                .thenReturn(Optional.ofNullable(user));
        User actualResult = deleteUserUseCase.deleteUser(1);
        assertEquals(expectedResult, actualResult);
        verify(userRepository).save(user);
        //verify(userRepository).deleteById(Long.valueOf(1));
    }
}