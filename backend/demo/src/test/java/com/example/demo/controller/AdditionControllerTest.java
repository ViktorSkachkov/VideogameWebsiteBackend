package com.example.demo.controller;

import com.example.demo.business.cases.additions.*;

import com.example.demo.domain.User;
import org.junit.jupiter.api.Test;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

//@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AdditionController.class)
class AdditionControllerTest {
    /*@Autowired
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
    private UpdateAdditionUseCase updateAdditionUseCase;*/
    @Test
    void GetAddition() throws Exception{
       /* User user = User.builder()
                .id(1)
                .username("username1")
                .email("email1")
                .role("role1")
                .bankAccount("bankAccount1")
                .build();
        assertEquals(user, getAdditionUseCase.GetAddition(1));*/
    }
}