package com.example.demo.controller;

import com.example.demo.business.cases.AccessTokenDecoder;
import com.example.demo.business.cases.AccessTokenEncoder;
import com.example.demo.business.cases.user.*;
import com.example.demo.domain.AccessToken;
import com.example.demo.domain.LoginResponse;
import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.persistence.entity.RolePersistence;
import com.example.demo.persistence.entity.UserPersistence;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GetUsersUseCase getUsersUseCase;
    @MockBean
    private GetUserUseCase getUserUseCase;
    @MockBean
    private AddUserUseCase addUserUseCase;
    @MockBean
    private UpdateUserUseCase updateUserUseCase;
    @MockBean
    private DeleteUserUseCase deleteUserUseCase;
    @MockBean
    private AccessTokenDecoder accessTokenDecoder;
    @MockBean
    private AccessTokenEncoder accessTokenEncoder;

    @Test
    @WithMockUser(username = "username1", password = "password", roles = {"CUSTOMER", "EMPLOYEE"})
    void addUser() throws Exception {
        User user = User.builder()
                .id(3)
                .username("username3")
                .pwd("password")
                .email("email3")
                .bankAccount("bankAccount3")
                .userRoles(Set.of(Role.builder()
                        .id(1)
                        .userId(3)
                        .role("EMPLOYEE")
                        .build()))
                .build();
        when(addUserUseCase.addUser(user))
                .thenReturn(user);
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                    {"id":3, "username":"username3", "pwd": "password", "email":"email3","bankAccount":"bankAccount3","userRoles":[{
                                        "id": 1,
                                        "user_id": 3,
                                        "role": "EMPLOYEE"
                                    }]
                                    }
                                """)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json("""
                             {"id":3, "username":"username3", "pwd": "password", "email":"email3","bankAccount":"bankAccount3","userRoles":[{
                                 "id": 1,
                                 "user_id": 3,
                                 "role": "EMPLOYEE"
                             }]}
                        """));
        verify(addUserUseCase).addUser(user);
    }

    @Test
    @WithMockUser(username = "username1", password = "password", roles = {"CUSTOMER", "EMPLOYEE"})
    void deleteUser() throws Exception {
        User user = User.builder()
                .id(1)
                .username("username1")
                .pwd("password")
                .email("email1")
                .bankAccount("bankAccount1")
                .userRoles(Set.of(Role.builder()
                        .id(1)
                        .userId(1)
                        .role("EMPLOYEE")
                        .build()))
                .build();
        when(deleteUserUseCase.deleteUser(1))
                .thenReturn(user);
        mockMvc.perform(delete("/users/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                                            {"id":1, "username":"username1", "pwd": "password", "email":"email1","bankAccount":"bankAccount1","userRoles":[{
                                                        "id": 1,
                                                        "user_id": 1,
                                                        "role": "EMPLOYEE"
                                                    }]}
                        """));
        verify(deleteUserUseCase).deleteUser(1);
    }

    @Test
    @WithMockUser(username = "username1", password = "password", roles = {"CUSTOMER", "EMPLOYEE"})
    void getUsers() throws Exception {
        User user1 = User.builder()
                .id(1)
                .username("username1")
                .pwd("password1")
                .email("email1")
                .bankAccount("bankAccount1")
                .userRoles(Set.of(Role.builder()
                        .id(1)
                        .userId(1)
                        .role("EMPLOYEE")
                        .build()))
                .build();
        User user2 = User.builder()
                .id(2)
                .username("username2")
                .pwd("password2")
                .email("email2")
                .bankAccount("bankAccount2")
                .userRoles(Set.of(Role.builder()
                        .id(1)
                        .userId(2)
                        .role("EMPLOYEE")
                        .build()))
                .build();
        when(getUsersUseCase.getUsers())
                .thenReturn(List.of(user1, user2));
        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                          [{"id":1, "username":"username1", "pwd": "password1", "email":"email1","bankAccount":"bankAccount1","userRoles":[{
                              "id": 1,
                              "user_id": 1,
                              "role": "EMPLOYEE"
                          }]},
                          {"id":2, "username":"username2", "pwd": "password2", "email":"email2","bankAccount":"bankAccount2","userRoles":[{
                              "id": 1,
                              "user_id": 2,
                              "role": "EMPLOYEE"
                          }]}]
                        """));
        verify(getUsersUseCase).getUsers();
    }

    @Test
    @WithMockUser(username = "username1", password = "password", roles = {"CUSTOMER", "EMPLOYEE"})
    void getUser() throws Exception {
        User user = User.builder()
                .id(1)
                .username("username1")
                .pwd("password")
                .email("email1")
                .bankAccount("bankAccount1")
                .userRoles(Set.of(Role.builder()
                        .id(1)
                        .userId(1)
                        .role("EMPLOYEE")
                        .build()))
                .build();
        when(getUserUseCase.getUser(1))
                .thenReturn(user);
        mockMvc.perform(get("/users/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                          {"id":1, "username":"username1", "pwd": "password", "email":"email1","bankAccount":"bankAccount1","userRoles":[{
                              "id": 1,
                              "user_id": 1,
                              "role": "EMPLOYEE"
                          }]}
                        """));
        verify(getUserUseCase).getUser(1);
    }

    @Test
    @WithMockUser(username = "username1", password = "password", roles = {"CUSTOMER", "EMPLOYEE"})
    void updateUser() throws Exception {
        User user = User.builder()
                .id(1)
                .username("username3")
                .pwd("password")
                .email("email3")
                .bankAccount("bankAccount3")
                .userRoles(Set.of(Role.builder()
                        .id(1)
                        .userId(1)
                        .role("EMPLOYEE")
                        .build()))
                .build();
        UserPersistence userPersistence = UserPersistence.builder()
                .id(1L)
                .username("username3")
                .pwd("password")
                .email("email3")
                .bankAccount("bankAccount3")
                .userRoles(Set.of(RolePersistence.builder()
                        .id(1L)
                        .user(1L)
                        .role("EMPLOYEE")
                        .build()))
                .build();
        String token = generateAccessToken(userPersistence);
        LoginResponse loginResponse = LoginResponse.builder().accessToken(token).build();
        when(updateUserUseCase.updateUser(user))
                .thenReturn(loginResponse);
        mockMvc.perform(put("/users")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                    {"id":1, "username":"username3", "pwd": "password", "email":"email3","bankAccount":"bankAccount3","userRoles":[{
                                        "id": 1,
                                        "user_id": 1,
                                        "role": "EMPLOYEE"
                                    }]}
                                """)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(print())
                /*.andExpect(content().json("""
                            {"id":1, "username":"username3", "pwd": "password", "email":"email3","bankAccount":"bankAccount3","userRoles":[{
                                "id": 1,
                                "user_id": 1,
                                "role": "EMPLOYEE"
                            }]}
                       """))*/;
        verify(updateUserUseCase).updateUser(user);
    }

    private String generateAccessToken(UserPersistence user) {
        Long userId = user != null ? user.getId() : null;
        List<String> roles = user.getUserRoles().stream()
                .map(userRole -> userRole.getRole())
                .toList();

        return accessTokenEncoder.encode(
                AccessToken.builder()
                        .subject(user.getUsername())
                        .roles(roles)
                        .userId(userId)
                        .build());
    }
}