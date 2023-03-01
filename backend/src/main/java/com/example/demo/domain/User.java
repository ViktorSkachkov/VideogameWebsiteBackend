package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class User {
    private int id;
    private String username;
    private String pwd;
    private String email;
    private String bankAccount;
    private String role;
}
