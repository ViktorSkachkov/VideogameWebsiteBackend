package com.example.demo.domain;

import lombok.*;

@AllArgsConstructor
@Data
@Builder
public class User {
    private int id;
    private String username;
    private String email;
    private String bankAccount;
    private String role;
}
