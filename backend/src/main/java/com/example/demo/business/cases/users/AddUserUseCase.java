package com.example.demo.business.cases.users;

import com.example.demo.domain.LoginResponse;
import com.example.demo.domain.User;

public interface AddUserUseCase {
    User AddUser(User user);
    //LoginResponse AddUser(User user);
}
