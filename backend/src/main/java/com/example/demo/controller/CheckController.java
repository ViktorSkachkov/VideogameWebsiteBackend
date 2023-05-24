package com.example.demo.controller;

import com.example.demo.configuration.security.isauthenticated.IsAuthenticated;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*")
@RequestMapping("/check")
@RequiredArgsConstructor
public class CheckController {

    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @GetMapping("")
    public int check() {
        return 1;
    }
}
