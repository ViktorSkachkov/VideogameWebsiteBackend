package com.example.demo.business.cases.user;

import com.example.demo.domain.User;

public interface DeleteUserUseCase {
    User DeleteUser(int index);
}
