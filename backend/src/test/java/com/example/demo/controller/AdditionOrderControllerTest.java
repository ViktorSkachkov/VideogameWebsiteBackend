package com.example.demo.controller;

import com.example.demo.business.cases.additionorder.AddAdditionOrderUseCase;
import com.example.demo.business.cases.additionorder.GetAdditionOrderUseCase;
import com.example.demo.business.cases.additionorder.GetAdditionOrdersByUserUseCase;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
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
}