package com.example.demo.controller;

import com.example.demo.business.cases.additionorder.AddAdditionOrderUseCase;
import com.example.demo.business.cases.additionorder.GetAdditionOrderUseCase;
import com.example.demo.business.cases.additionorder.GetAdditionOrdersByUserUseCase;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @Test
    @WithMockUser(username="username1", password = "password", roles = {"CUSTOMER"})
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
    @WithMockUser(username="username1", password = "password", roles = {"CUSTOMER"})
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
    @WithMockUser(username="username1", password = "password", roles = {"CUSTOMER"})
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
}