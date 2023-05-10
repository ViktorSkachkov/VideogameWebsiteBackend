package com.example.demo.controller;

import com.example.demo.business.cases.gameorder.AddGameOrderUseCase;
import com.example.demo.business.cases.gameorder.GetGameOrderUseCase;
import com.example.demo.business.cases.gameorder.GetGameOrdersByUserUseCase;
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
class GameOrderControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AddGameOrderUseCase addGameOrderUseCase;
    @MockBean
    private GetGameOrderUseCase getGameOrderUseCase;
    @MockBean
    private GetGameOrdersByUserUseCase getGameOrdersByUserUseCase;
}