package com.example.demo.controller;

import com.example.demo.business.cases.additionorder.*;
import com.example.demo.domain.AdditionOrder;
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
class AdditionOrderControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AddAdditionOrderUseCase addAdditionOrderUseCase;
    @MockBean
    private GetAdditionOrdersByUserUseCase getAdditionOrdersByUserUseCase;
    @MockBean
    private GetAdditionOrderUseCase getAdditionOrderUseCase;
    @MockBean
    private GetAdditionCartItemsUseCase getAdditionCartItemsUseCase;
    @MockBean
    private ConfirmAdditionOrderUseCase confirmAdditionOrderUseCase;
    @MockBean
    private DeleteAdditionOrderUseCase deleteAdditionOrderUseCase;
    @MockBean
    private DecreaseAdditionOrderUnitsUseCase decreaseAdditionOrderUnitsUseCase;
    @MockBean
    private IncreaseAdditionOrderUnitsUseCase increaseAdditionOrderUnitsUseCase;


    @Test
    @WithMockUser(username = "username1", password = "password", roles = {"CUSTOMER"})
    void addAdditionOrder() throws Exception {
        AdditionOrder additionOrder = AdditionOrder.builder()
                .id(1)
                .units(3)
                .addition(43)
                .user(41)
                .build();
        when(addAdditionOrderUseCase.addAdditionOrder(additionOrder))
                .thenReturn(additionOrder);
        mockMvc.perform(post("/additionOrders")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                    {"id":1, "units":3, "addition":43,"user":41}
                                """)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json("""
                            {"id":1, "units":3, "addition":43,"user":41}
                        """));
        verify(addAdditionOrderUseCase).addAdditionOrder(additionOrder);
    }

    @Test
    @WithMockUser(username = "username1", password = "password", roles = {"CUSTOMER"})
    void getAdditionOrdersByUser() throws Exception {
        AdditionOrder additionOrder1 = AdditionOrder.builder()
                .id(1)
                .units(3)
                .addition(43)
                .user(41)
                .build();
        AdditionOrder additionOrder2 = AdditionOrder.builder()
                .id(2)
                .units(3)
                .addition(43)
                .user(41)
                .build();
        when(getAdditionOrdersByUserUseCase.getAdditionOrdersByUser(41))
                .thenReturn(List.of(additionOrder1, additionOrder2));
        mockMvc.perform(get("/additionOrders/getByUser/41"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                                            [{"id":1, "units":3, "addition":43,"user":41},
                                            {"id":2, "units":3, "addition":43,"user":41}]
                        """));
        verify(getAdditionOrdersByUserUseCase).getAdditionOrdersByUser(41);
    }

    @Test
    @WithMockUser(username = "username1", password = "password", roles = {"CUSTOMER"})
    void getAdditionOrder() throws Exception {
        AdditionOrder additionOrder = AdditionOrder.builder()
                .id(1)
                .units(3)
                .addition(43)
                .user(41)
                .build();
        when(getAdditionOrderUseCase.getAdditionOrder(1))
                .thenReturn(additionOrder);
        mockMvc.perform(get("/additionOrders/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                               {"id":1, "units":3, "addition":43,"user":41}
                        """));
        verify(getAdditionOrderUseCase).getAdditionOrder(1);
    }

    @Test
    @WithMockUser(username = "username1", password = "password", roles = {"CUSTOMER"})
    void getAdditionCartItems() throws Exception {
        AdditionOrder additionOrder1 = AdditionOrder.builder()
                .id(1)
                .units(3)
                .addition(43)
                .user(45)
                .approved(false)
                .time(LocalDateTime.of(2017, 12, 13, 15, 56, 30))
                .dateFormatted("d")
                .totalPrice(30)
                .build();
        when(getAdditionCartItemsUseCase.getAdditionCartItems(45))
                .thenReturn(List.of(additionOrder1));
        mockMvc.perform(get("/additionOrders/cart/45"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                                            [{"id":1, "units":3, "addition":43,"user":45,"approved":false,
                                            "time":"2017-12-13T15:56:30","totalPrice":30,"dateFormatted":"d"}]
                        """));
        verify(getAdditionCartItemsUseCase).getAdditionCartItems(45);
    }

    @Test
    @WithMockUser(username = "username1", password = "password", roles = {"CUSTOMER"})
    void confirmAdditionOrders() throws Exception {
        AdditionOrder additionOrder = AdditionOrder.builder()
                .id(1)
                .units(3)
                .addition(43)
                .user(41)
                .approved(false)
                .time(LocalDateTime.of(2017, 12, 13, 15, 56, 30))
                .dateFormatted("d")
                .totalPrice(30)
                .build();
        when(confirmAdditionOrderUseCase.confirmAdditionOrder(additionOrder.getUser()))
                .thenReturn(additionOrder);
        mockMvc.perform(put("/additionOrders/41")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                    {"id":1, "units":3, "addition":43,"user":41,"approved":false,
                                            "time":"2017-12-13T15:56:30","totalPrice":30,"dateFormatted":"d"}
                                """)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json("""
                               {"id":1, "units":3, "addition":43,"user":41,"approved":false,
                                            "time":"2017-12-13T15:56:30","totalPrice":30,"dateFormatted":"d"}
                        """));
        verify(confirmAdditionOrderUseCase).confirmAdditionOrder(additionOrder.getUser());
    }


    @Test
    @WithMockUser(username = "username1", password = "password", roles = {"CUSTOMER"})
    void deleteGameOrder() throws Exception {
        AdditionOrder additionOrder = AdditionOrder.builder()
                .id(1)
                .units(3)
                .addition(43)
                .user(41)
                .approved(false)
                .time(LocalDateTime.of(2017, 12, 13, 15, 56, 30))
                .dateFormatted("d")
                .totalPrice(30)
                .build();
        when(deleteAdditionOrderUseCase.deleteAddition(1))
                .thenReturn(additionOrder);
        mockMvc.perform(delete("/additionOrders/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                              {"id":1}
                        """));
        verify(deleteAdditionOrderUseCase).deleteAddition(1);
    }

    @Test
    @WithMockUser(username = "username1", password = "password", roles = {"CUSTOMER"})
    void increaseGameOrderUnits() throws Exception {
        AdditionOrder additionOrder = AdditionOrder.builder()
                .id(1)
                .units(3)
                .addition(43)
                .user(41)
                .approved(false)
                .time(LocalDateTime.of(2017, 12, 13, 15, 56, 30))
                .build();

        AdditionOrder additionOrder2 = AdditionOrder.builder()
                .id(1)
                .units(4)
                .addition(43)
                .user(41)
                .approved(false)
                .time(LocalDateTime.of(2017, 12, 13, 15, 56, 30))
                .build();
        when(increaseAdditionOrderUnitsUseCase.increaseAdditionOrderUnits(1L))
                .thenReturn(additionOrder2);
        mockMvc.perform(put("/additionOrders/increase/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                    {"id":1, "units":3, "addition":43,"user":41,"approved":false,
                                            "time":"2017-12-13T15:56:30"}
                                """)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json("""
                               {"id":1, "units":4, "addition":43,"user":41,"approved":false,
                                            "time":"2017-12-13T15:56:30"}
                        """));
        verify(increaseAdditionOrderUnitsUseCase).increaseAdditionOrderUnits((long) additionOrder.getId());
    }

    @Test
    @WithMockUser(username = "username1", password = "password", roles = {"CUSTOMER"})
    void decreaseGameOrderUnits() throws Exception {
        AdditionOrder additionOrder = AdditionOrder.builder()
                .id(1)
                .units(3)
                .addition(43)
                .user(41)
                .approved(false)
                .time(LocalDateTime.of(2017, 12, 13, 15, 56, 30))
                .build();

        AdditionOrder additionOrder2 = AdditionOrder.builder()
                .id(1)
                .units(2)
                .addition(43)
                .user(41)
                .approved(false)
                .time(LocalDateTime.of(2017, 12, 13, 15, 56, 30))
                .build();
        when(decreaseAdditionOrderUnitsUseCase.decreaseAdditionOrderUnits(1L))
                .thenReturn(additionOrder2);
        mockMvc.perform(put("/additionOrders/decrease/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                    {"id":1, "units":3, "addition":43,"user":41,"approved":false,
                                            "time":"2017-12-13T15:56:30"}
                                """)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json("""
                               {"id":1, "units":2, "addition":43,"user":41,"approved":false,
                                            "time":"2017-12-13T15:56:30"}
                        """));
        verify(decreaseAdditionOrderUnitsUseCase).decreaseAdditionOrderUnits((long) additionOrder.getId());
    }
}