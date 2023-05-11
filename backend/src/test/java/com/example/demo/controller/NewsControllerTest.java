package com.example.demo.controller;

import com.example.demo.business.cases.AccessTokenDecoder;
import com.example.demo.business.cases.AccessTokenEncoder;
import com.example.demo.business.cases.news.*;
import com.example.demo.domain.News;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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
public class NewsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AddNewsUseCase addNewsUseCase;
    @MockBean
    private GetNewsByGameUseCase getNewsByGameUseCase;
    @MockBean
    private GetNewsUseCase getNewsUseCase;
    @MockBean
    private GetOneNewsUseCase getOneNewsUseCase;
    @MockBean
    private DeleteNewsUseCase deleteNewsUseCase;
    @MockBean
    private UpdateNewsUseCase updateNewsUseCase;
    @MockBean
    private AccessTokenDecoder accessTokenDecoder;
    @MockBean
    private AccessTokenEncoder accessTokenEncoder;


    @Test
    @WithMockUser(username="username1", password = "password", roles = {"EMPLOYEE"})
    void addNews() throws Exception{
        News news = News.builder()
                .id(3)
                .gameId(1)
                .text("text3")
                .title("title3")
                .build();
        when(addNewsUseCase.addNews(news))
                .thenReturn(news);
        mockMvc.perform(post("/news")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                            {"id":3, "gameId":1, "text":"text3", "title":"title3"}
                        """)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json("""
                            {"id":3, "gameId":1, "text":"text3", "title":"title3"}
                       """));
        verify(addNewsUseCase).addNews(news);
    }
    @Test
    @WithMockUser(username="username1", password = "password", roles = {"CUSTOMER", "EMPLOYEE"})
    void getNewsByGame() throws Exception{
        News news1 = News.builder()
                .id(1)
                .gameId(1)
                .text("text1")
                .title("title1")
                .build();
        News news2 = News.builder()
                .id(2)
                .gameId(1)
                .text("text2")
                .title("title2")
                .build();
        when(getNewsByGameUseCase.getNewsByGame(1))
                .thenReturn(List.of(news1, news2));
        mockMvc.perform(get("/news/getByGame/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            [{"id":1, "gameId":1, "text":"text1", "title":"title1"},
                            {"id":2, "gameId":1, "text":"text2", "title":"title2"}]
                          """));
        verify(getNewsByGameUseCase).getNewsByGame(1);
    }
    @Test
    @WithMockUser(username="username1", password = "password", roles = {"CUSTOMER", "EMPLOYEE"})
    void getNews() throws Exception{
        News news1 = News.builder()
                .id(1)
                .gameId(1)
                .text("text1")
                .title("title1")
                .build();
        News news2 = News.builder()
                .id(2)
                .gameId(1)
                .text("text2")
                .title("title2")
                .build();
        when(getNewsUseCase.getNews())
                .thenReturn(List.of(news1, news2));
        mockMvc.perform(get("/news"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            [{"id":1, "gameId":1, "text":"text1", "title":"title1"},
                            {"id":2, "gameId":1, "text":"text2", "title":"title2"}]
                          """));
        verify(getNewsUseCase).getNews();
    }
    @Test
    @WithMockUser(username="username1", password = "password", roles = {"CUSTOMER", "EMPLOYEE"})
    void getOneNews() throws Exception{
        News news = News.builder()
                .id(1)
                .gameId(1)
                .text("text1")
                .title("title1")
                .build();
        when(getOneNewsUseCase.getOneNews(1))
                .thenReturn(news);
        mockMvc.perform(get("/news/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {"id":1, "gameId":1, "text":"text1", "title":"title1"}
                          """));
        verify(getOneNewsUseCase).getOneNews(1);
    }
    @Test
    @WithMockUser(username="username1", password = "password", roles = {"EMPLOYEE"})
    void deleteNews() throws Exception{
        News news = News.builder()
                .id(1)
                .gameId(1)
                .text("text1")
                .title("title1")
                .build();
        when(deleteNewsUseCase.deleteNews(1))
                .thenReturn(news);
        mockMvc.perform(delete("/news/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                     {"id":1, "gameId":1, "text":"text1", "title":"title1"}
"""));
        verify(deleteNewsUseCase).deleteNews(1);
    }
    @Test
    @WithMockUser(username="username1", password = "password", roles = {"EMPLOYEE"})
    void updateNews() throws Exception{
        News news = News.builder()
                .id(1)
                .gameId(1)
                .text("text3")
                .title("title3")
                .build();
        when(updateNewsUseCase.updateNews(news))
                .thenReturn(news);
        mockMvc.perform(put("/news")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                            {"id":1, "gameId":1, "text":"text3", "title":"title3"}
                        """)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json("""
                            {"id":1, "gameId":1, "text":"text3", "title":"title3"}
                       """));
        verify(updateNewsUseCase).updateNews(news);
    }
}
