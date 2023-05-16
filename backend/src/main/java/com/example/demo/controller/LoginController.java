package com.example.demo.controller;

import com.example.demo.business.cases.LoginUseCase;
import com.example.demo.domain.LoginRequest;
import com.example.demo.domain.LoginResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*")
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginUseCase loginUseCase;

    /**
     * @param loginRequest
     * @return
     */
    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        LoginResponse loginResponse = loginUseCase.login(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }
}
