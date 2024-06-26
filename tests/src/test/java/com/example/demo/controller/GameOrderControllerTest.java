package com.example.demo.controller;

import com.example.demo.business.cases.gameorder.*;
import com.example.demo.domain.GameOrder;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
class GameOrderControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AddGameOrderUseCase addGameOrderUseCase;
    @MockBean
    private GetGameOrderUseCase getGameOrderUseCase;
    @MockBean
    private GetGameOrdersByUserUseCase getGameOrdersByUserUseCase;
    @MockBean
    private GetGameCartItemsUseCase getGameCartItemsUseCase;
    @MockBean
    private ConfirmGameOrderUseCase confirmGameOrderUseCase;
    @MockBean
    private DeleteGameOrderUseCase deleteGameOrderUseCase;
    @MockBean
    private IncreaseGameOrderUnitsUseCase increaseGameOrderUnitsUseCase;
    @MockBean
    private DecreaseGameOrderUnitsUseCase decreaseGameOrderUnitsUseCase;

    @Test
    @WithMockUser(username = "username1", password = "password", roles = {"CUSTOMER"})
    void addGameOrder() throws Exception {
        GameOrder gameOrder = GameOrder.builder()
                .id(1)
                .units(3)
                .game(23)
                .user(41)
                .build();
        when(addGameOrderUseCase.addGameOrder(gameOrder))
                .thenReturn(gameOrder);
        mockMvc.perform(post("/gameOrders")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                    {"id":1, "units":3, "game":23,"user":41}
                                """)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json("""
                            {"id":1, "units":3, "game":23,"user":41}
                        """));
        verify(addGameOrderUseCase).addGameOrder(gameOrder);
    }

    @Test
    @WithMockUser(username = "username1", password = "password", roles = {"CUSTOMER"})
    void getGameOrdersByUser() throws Exception {
        GameOrder gameOrder1 = GameOrder.builder()
                .id(1)
                .units(3)
                .game(23)
                .user(41)
                .build();
        GameOrder gameOrder2 = GameOrder.builder()
                .id(2)
                .units(3)
                .game(23)
                .user(41)
                .build();
        when(getGameOrdersByUserUseCase.getGameOrdersByUser(41))
                .thenReturn(List.of(gameOrder1, gameOrder2));
        mockMvc.perform(get("/gameOrders/getByUser/41"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                                            [{"id":1, "units":3, "game":23,"user":41},
                                            {"id":2, "units":3, "game":23,"user":41}]
                        """));
        verify(getGameOrdersByUserUseCase).getGameOrdersByUser(41);
    }

    @Test
    @WithMockUser(username = "username1", password = "password", roles = {"CUSTOMER", "EMPLOYEE"})
    void getGameOrder() throws Exception {
        LocalDateTime time = LocalDateTime.of(2017, 12, 13, 15, 56, 30);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDateTime = time.format(dateTimeFormatter);

        GameOrder gameOrder = GameOrder.builder()
                .id(1)
                .units(3)
                .game(23)
                .user(41)
                .time(time)
                .dateFormatted(formattedDateTime)
                .build();
        when(getGameOrderUseCase.getGameOrder(1))
                .thenReturn(gameOrder);
        mockMvc.perform(get("/gameOrders/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                               {"id":1, "units":3, "game":23,"user":41}
                        """));
        verify(getGameOrderUseCase).getGameOrder(1);
    }

    @Test
    @WithMockUser(username = "username1", password = "password", roles = {"CUSTOMER"})
    void getGameCartItems() throws Exception {
        GameOrder gameOrder = GameOrder.builder()
                .id(1)
                .units(3)
                .game(43)
                .user(45)
                .approved(false)
                .time(LocalDateTime.of(2017, 12, 13, 15, 56, 30))
                .dateFormatted("d")
                .totalPrice(30)
                .build();
        when(getGameCartItemsUseCase.getGameCartItems(45))
                .thenReturn(List.of(gameOrder));
        mockMvc.perform(get("/gameOrders/cart/45"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                                            [{"id":1, "units":3, "game":43,"user":45,"approved":false,
                                            "time":"2017-12-13T15:56:30","totalPrice":30,"dateFormatted":"d"}]
                        """));
        verify(getGameCartItemsUseCase).getGameCartItems(45);
    }

    @Test
    @WithMockUser(username = "username1", password = "password", roles = {"CUSTOMER"})
    void confirmGameOrders() throws Exception {
        GameOrder gameOrder = GameOrder.builder()
                .id(1)
                .units(3)
                .game(43)
                .user(41)
                .approved(false)
                .time(LocalDateTime.of(2017, 12, 13, 15, 56, 30))
                .dateFormatted("d")
                .totalPrice(30)
                .build();
        when(confirmGameOrderUseCase.confirmGameOrder(gameOrder.getUser()))
                .thenReturn(gameOrder);
        mockMvc.perform(put("/gameOrders/41")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                    {"id":1, "units":3, "game":43,"user":41,"approved":false,
                                            "time":"2017-12-13T15:56:30","totalPrice":30,"dateFormatted":"d"}
                                """)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json("""
                               {"id":1, "units":3, "game":43,"user":41,"approved":false,
                                            "time":"2017-12-13T15:56:30","totalPrice":30,"dateFormatted":"d"}
                        """));
        verify(confirmGameOrderUseCase).confirmGameOrder(gameOrder.getUser());
    }

    @Test
    @WithMockUser(username = "username1", password = "password", roles = {"CUSTOMER"})
    void deleteGameOrder() throws Exception {
        GameOrder gameOrder = GameOrder.builder()
                .id(1)
                .units(3)
                .game(43)
                .user(41)
                .approved(false)
                .time(LocalDateTime.of(2017, 12, 13, 15, 56, 30))
                .dateFormatted("d")
                .totalPrice(30)
                .build();
        when(deleteGameOrderUseCase.deleteGame(1))
                .thenReturn(gameOrder);
        mockMvc.perform(delete("/gameOrders/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                              {"id":1}
                        """));
        verify(deleteGameOrderUseCase).deleteGame(1);
    }

    @Test
    @WithMockUser(username = "username1", password = "password", roles = {"CUSTOMER"})
    void increaseGameOrderUnits() throws Exception {
        GameOrder gameOrder = GameOrder.builder()
                .id(1)
                .units(3)
                .game(43)
                .user(41)
                .approved(false)
                .time(LocalDateTime.of(2017, 12, 13, 15, 56, 30))
                .build();

        GameOrder gameOrder2 = GameOrder.builder()
                .id(1)
                .units(4)
                .game(43)
                .user(41)
                .approved(false)
                .time(LocalDateTime.of(2017, 12, 13, 15, 56, 30))
                .build();
        when(increaseGameOrderUnitsUseCase.increaseGameOrderUnits(1))
                .thenReturn(gameOrder2);
        mockMvc.perform(put("/gameOrders/increase/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                    {"id":1, "units":3, "game":43,"user":41,"approved":false,
                                            "time":"2017-12-13T15:56:30"}
                                """)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json("""
                               {"id":1, "units":4, "game":43,"user":41,"approved":false,
                                            "time":"2017-12-13T15:56:30"}
                        """));
        verify(increaseGameOrderUnitsUseCase).increaseGameOrderUnits(gameOrder.getId());
    }

    @Test
    @WithMockUser(username = "username1", password = "password", roles = {"CUSTOMER"})
    void decreaseGameOrderUnits() throws Exception {
        GameOrder gameOrder = GameOrder.builder()
                .id(1)
                .units(3)
                .game(43)
                .user(41)
                .approved(false)
                .time(LocalDateTime.of(2017, 12, 13, 15, 56, 30))
                .build();

        GameOrder gameOrder2 = GameOrder.builder()
                .id(1)
                .units(2)
                .game(43)
                .user(41)
                .approved(false)
                .time(LocalDateTime.of(2017, 12, 13, 15, 56, 30))
                .build();
        when(decreaseGameOrderUnitsUseCase.decreaseGameOrderUnits(1))
                .thenReturn(gameOrder2);
        mockMvc.perform(put("/gameOrders/decrease/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                    {"id":1, "units":3, "game":43,"user":41,"approved":false,
                                            "time":"2017-12-13T15:56:30"}
                                """)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json("""
                               {"id":1, "units":2, "game":43,"user":41,"approved":false,
                                            "time":"2017-12-13T15:56:30"}
                        """));
        verify(decreaseGameOrderUnitsUseCase).decreaseGameOrderUnits(gameOrder.getId());
    }
}