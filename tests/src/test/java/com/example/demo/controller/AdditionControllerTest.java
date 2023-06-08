package com.example.demo.controller;

import com.example.demo.business.cases.AccessTokenDecoder;
import com.example.demo.business.cases.AccessTokenEncoder;
import com.example.demo.business.cases.LoginUseCase;
import com.example.demo.business.cases.addition.*;
import com.example.demo.domain.Addition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class AdditionControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GetAdditionsUseCase getAdditionsUseCase;
    @MockBean
    private GetAdditionUseCase getAdditionUseCase;
    @MockBean
    private AddAdditionUseCase addAdditionUseCase;
    @MockBean
    private DeleteAdditionUseCase deleteAdditionUseCase;
    @MockBean
    private UpdateAdditionUseCase updateAdditionUseCase;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private AccessTokenEncoder accessTokenEncoder;
    @MockBean
    private AccessTokenDecoder AccessTokendecoder;
    @MockBean
    private LoginUseCase loginUseCase;

    @Test
    @WithMockUser(username = "username1", password = "password", roles = {"CUSTOMER", "EMPLOYEE"})
    void addAddition() throws Exception {
        Addition addition = Addition.builder()
                .id(3)
                .gameId(2)
                .name("name3")
                .price(15)
                .description("description3")
                .image("image3")
                .build();
        when(addAdditionUseCase.addAddition(addition))
                .thenReturn(addition);
        mockMvc.perform(post("/additions")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                    {"id":3, "gameId":2, "name":"name3","price":15,"description":"description3","image":"image3"}
                                """)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json("""
                             {"id":3, "gameId":2, "name":"name3","price":15,"description":"description3","image":"image3"}
                        """));
        verify(addAdditionUseCase).addAddition(addition);
    }

    @Test
    @WithMockUser(username = "username1", password = "password", roles = {"CUSTOMER", "EMPLOYEE"})
    void deleteAddition() throws Exception {
        Addition addition1 = Addition.builder()
                .id(1)
                .gameId(1)
                .name("name1")
                .price(10)
                .description("description1")
                .image("image1")
                .build();
        when(deleteAdditionUseCase.deleteAddition(1))
                .thenReturn(addition1);
        mockMvc.perform(delete("/additions/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                              {"id":1, "gameId":1, "name":"name1","price":10,"description":"description1","image":"image1"}
                        """));
        verify(deleteAdditionUseCase).deleteAddition(1);
    }

    @Test
    @WithMockUser(username = "username1", password = "password", roles = {"CUSTOMER", "EMPLOYEE"})
    void getAdditions() throws Exception {
        Addition addition1 = Addition.builder()
                .id(1)
                .gameId(1)
                .name("name1")
                .price(10)
                .description("description1")
                .image("image1")
                .build();
        Addition addition2 = Addition.builder()
                .id(2)
                .gameId(1)
                .name("name2")
                .price(10)
                .description("description2")
                .image("image2")
                .build();
        when(getAdditionsUseCase.getAdditions())
                .thenReturn(List.of(addition1, addition2));
        mockMvc.perform(get("/additions"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                //.andExpect(header().string("Authorization", "Bearer " + token))
                .andExpect(content().json("""
                          [{"id":1, "gameId":1, "name":"name1","price":10,"description":"description1","image":"image1"},
                          {"id":2, "gameId":1, "name":"name2","price":10,"description":"description2","image":"image2"}]
                        """));
        verify(getAdditionsUseCase).getAdditions();
    }

    @Test
    @WithMockUser(username = "username1", password = "password", roles = {"CUSTOMER", "EMPLOYEE"})
    void getAddition() throws Exception {
        Addition addition = Addition.builder()
                .id(1)
                .gameId(1)
                .name("name1")
                .price(10)
                .description("description1")
                .image("image1")
                .build();
        when(getAdditionUseCase.getAddition(1))
                .thenReturn(addition);
        mockMvc.perform(get("/additions/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                              {"id":1, "gameId":1, "name":"name1","price":10,"description":"description1","image":"image1"}
                        """));
        verify(getAdditionUseCase).getAddition(1);
    }

    @Test
    @WithMockUser(username = "username1", password = "password", roles = {"CUSTOMER", "EMPLOYEE"})
    void updateAddition() throws Exception {
        Addition addition = Addition.builder()
                .id(1)
                .gameId(2)
                .name("name3")
                .price(15)
                .description("description3")
                .image("image3")
                .build();
        when(updateAdditionUseCase.updateAddition(addition))
                .thenReturn(addition);
        mockMvc.perform(put("/additions")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                    {"id":1, "gameId":2, "name":"name3","price":15,"description":"description3","image":"image3"}
                                """)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json("""
                             {"id":1, "gameId":2, "name":"name3","price":15,"description":"description3","image":"image3"}
                        """));
        verify(updateAdditionUseCase).updateAddition(addition);
    }
}