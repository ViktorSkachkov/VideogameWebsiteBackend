package com.example.demo.persistence.repositories;

import com.example.demo.domain.User;

import java.util.List;

public interface UserRepository {
    List<User> GetUsers();
    User GetUser(int index);
    User AddUser(User addition);
    User UpdateUser(User addition);
    User DeleteUser(int index);
}
