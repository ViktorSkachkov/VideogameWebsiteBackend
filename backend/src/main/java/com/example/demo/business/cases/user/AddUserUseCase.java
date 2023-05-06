package com.example.demo.business.cases.user;

import com.example.demo.domain.User;

public interface AddUserUseCase {
    User AddUser(User user);
    //LoginResponse AddUser(User user);
}
