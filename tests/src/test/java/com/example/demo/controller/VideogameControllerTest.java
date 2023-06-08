package com.example.demo.controller;

import com.example.demo.business.cases.videogame.*;
import com.example.demo.domain.Videogame;
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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class VideogameControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GetVideogamesUseCase getVideogamesUseCase;
    @MockBean
    private GetVideogameUseCase getVideogameUseCase;
    @MockBean
    private AddVideogameUseCase addVideogameUseCase;
    @MockBean
    private UpdateVideogameUseCase updateVideogameUseCase;
    @MockBean
    private DeleteVideogameUseCase deleteVideogameUseCase;
    @MockBean
    private GetFeaturedVideogamesUseCase getFeaturedVideogamesUseCase;
    @MockBean
    private GetVideogamesForAdditionsFilterUseCase getVideogamesForAdditionsFilterUseCase;
    @MockBean
    private GetVideogamesForNewsFilterUseCase getVideogamesForNewsFilterUseCase;

    @Test
    @WithMockUser(username = "username1", password = "password", roles = {"CUSTOMER", "EMPLOYEE"})
    void addVideogame() throws Exception {
        Videogame videogame = Videogame.builder()
                .id(5)
                .name("name5")
                .price(15)
                .description("description5")
                .featured(false)
                .image("image5")
                .build();
        when(addVideogameUseCase.addVideogame(videogame))
                .thenReturn(videogame);
        mockMvc.perform(post("/videogames")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                    {"id":5, "name":"name5", "price":15,"description":"description5","featured":false,"image":"image5"}
                                """)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json("""
                             {"id":5, "name":"name5", "price":15,"description":"description5","featured":false,"image":"image5"}
                        """));
        verify(addVideogameUseCase).addVideogame(videogame);
    }

    @Test
    @WithMockUser(username = "username1", password = "password", roles = {"CUSTOMER", "EMPLOYEE"})
    void deleteVideogame() throws Exception {
        Videogame videogame = Videogame.builder()
                .id(1)
                .name("name1")
                .price(10)
                .description("description1")
                .image("image1")
                .build();
        when(deleteVideogameUseCase.deleteVideogame(1))
                .thenReturn(videogame);
        mockMvc.perform(delete("/videogames/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                                            {"id":1, "name":"name1", "price":10,"description":"description1","image":"image1"}
                        """));
        verify(deleteVideogameUseCase).deleteVideogame(1);
    }

    @Test
    @WithMockUser(username = "username1", password = "password", roles = {"CUSTOMER", "EMPLOYEE"})
    void getVideogames() throws Exception {
        Videogame videogame1 = Videogame.builder()
                .id(1)
                .name("name1")
                .price(10)
                .description("description1")
                .image("image1")
                .build();
        Videogame videogame2 = Videogame.builder()
                .id(2)
                .name("name2")
                .price(10)
                .description("description2")
                .image("image2")
                .build();
        when(getVideogamesUseCase.getVideogames())
                .thenReturn(List.of(videogame1, videogame2));
        mockMvc.perform(get("/videogames"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                                            [{"id":1, "name":"name1", "price":10,"description":"description1","image":"image1"},
                                            {"id":2, "name":"name2", "price":10,"description":"description2","image":"image2"}]
                        """));
        verify(getVideogamesUseCase).getVideogames();
    }

    @Test
    @WithMockUser(username = "username1", password = "password", roles = {"CUSTOMER", "EMPLOYEE"})
    void getVideogame() throws Exception {
        Videogame videogame = Videogame.builder()
                .id(1)
                .name("name1")
                .price(10)
                .description("description1")
                .image("image1")
                .build();
        when(getVideogameUseCase.getVideogame(1))
                .thenReturn(videogame);
        mockMvc.perform(get("/videogames/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {"id":1, "name":"name1", "price":10,"description":"description1","image":"image1"}
                        """));
        verify(getVideogameUseCase).getVideogame(1);
    }

    @Test
    @WithMockUser(username = "username1", password = "password", roles = {"CUSTOMER", "EMPLOYEE"})
    void getFeaturedVideogames() throws Exception {
        Videogame videogame1 = Videogame.builder()
                .id(1)
                .name("name1")
                .price(10)
                .description("description1")
                .featured(true)
                .image("image1")
                .build();
        Videogame videogame2 = Videogame.builder()
                .id(2)
                .name("name2")
                .price(10)
                .description("description2")
                .featured(true)
                .image("image2")
                .build();
        when(getFeaturedVideogamesUseCase.getFeaturedVideogames())
                .thenReturn(List.of(videogame1, videogame2));
        mockMvc.perform(get("/videogames/featured"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                                            [{"id":1, "name":"name1", "price":10,"description":"description1","featured":true, "image":"image1"},
                                            {"id":2, "name":"name2", "price":10,"description":"description2","featured":true, "image":"image2"}]
                        """));
        verify(getFeaturedVideogamesUseCase).getFeaturedVideogames();
    }

    @Test
    @WithMockUser(username = "username1", password = "password", roles = {"CUSTOMER", "EMPLOYEE"})
    void updateVideogame() throws Exception {
        Videogame videogame = Videogame.builder()
                .id(1)
                .name("name3")
                .price(15)
                .description("description3")
                .image("image3")
                .build();
        when(updateVideogameUseCase.updateVideogame(videogame))
                .thenReturn(videogame);
        mockMvc.perform(put("/videogames")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                    {"id":1, "name":"name3", "price":15,"description":"description3","image":"image3"}
                                """)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json("""
                             {"id":1, "name":"name3", "price":15,"description":"description3","image":"image3"}
                        """));
        verify(updateVideogameUseCase).updateVideogame(videogame);
    }

    @Test
    @WithMockUser(username = "username1", password = "password", roles = {"CUSTOMER", "EMPLOYEE"})
    void getVideogamesForAdditionsFilter() throws Exception {
        Videogame videogame1 = Videogame.builder()
                .id(1)
                .name("name1")
                .price(10)
                .description("description1")
                .image("image1")
                .build();
        Videogame videogame2 = Videogame.builder()
                .id(2)
                .name("name2")
                .price(10)
                .description("description2")
                .image("image2")
                .build();
        when(getVideogamesUseCase.getVideogames())
                .thenReturn(List.of(videogame1, videogame2));
        mockMvc.perform(get("/videogames"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                                            [{"id":1, "name":"name1", "price":10,"description":"description1","image":"image1"},
                                            {"id":2, "name":"name2", "price":10,"description":"description2","image":"image2"}]
                        """));
        verify(getVideogamesUseCase).getVideogames();
    }

    @Test
    @WithMockUser(username = "username1", password = "password", roles = {"CUSTOMER", "EMPLOYEE"})
    void getVideogamesForNewsFilter() throws Exception {
        Videogame videogame1 = Videogame.builder()
                .id(1)
                .name("name1")
                .price(10)
                .description("description1")
                .image("image1")
                .build();
        Videogame videogame2 = Videogame.builder()
                .id(2)
                .name("name2")
                .price(10)
                .description("description2")
                .image("image2")
                .build();
        when(getVideogamesUseCase.getVideogames())
                .thenReturn(List.of(videogame1, videogame2));
        mockMvc.perform(get("/videogames"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                                            [{"id":1, "name":"name1", "price":10,"description":"description1","image":"image1"},
                                            {"id":2, "name":"name2", "price":10,"description":"description2","image":"image2"}]
                        """));
        verify(getVideogamesUseCase).getVideogames();
    }
}