package com.example.demo.business.cases.user;

import com.example.demo.domain.LoginResponse;
import com.example.demo.domain.User;

public interface UpdateUserUseCase {
    LoginResponse UpdateUser(User user);
}
