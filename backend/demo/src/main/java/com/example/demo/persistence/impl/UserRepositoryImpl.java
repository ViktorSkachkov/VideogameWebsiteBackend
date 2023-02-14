package com.example.demo.persistence.impl;

import com.example.demo.domain.User;
import com.example.demo.persistence.repositories.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    List<User> users = new ArrayList<>();
    public UserRepositoryImpl() {
        users.add(User.builder()
                        .id(1)
                        .username("username1")
                        .email("email1")
                        .bankAccount("bankAccount1")
                        .role("role1")
                .build());
        users.add(User.builder()
                .id(2)
                .username("username2")
                .email("email2")
                .bankAccount("bankAccount2")
                .role("role2")
                .build());
    }
    @Override
    public List<User> GetUsers() {
        return users;
    }

    @Override
    public User GetUser(int index) {
        User returnUser = User.builder().build();
        for(User u : users) {
            if(u.getId() == index) {
                returnUser = u;
            }
        }
        return returnUser;
    }

    @Override
    public User AddUser(User addition) {
        users.add(addition);
        User returnUser = User.builder().build();
        for(User u : users) {
            if(u.getId() == addition.getId()) {
                returnUser = u;
            }
        }
        return returnUser;
    }

    @Override
    public User UpdateUser(User addition) {
        User returnUser = User.builder().build();
        for(User u : users) {
            if(u.getId() == addition.getId()) {
                returnUser = u;
            }
        }
        int index = users.indexOf(returnUser);
        users.remove(index);
        users.add(index, addition);
        return returnUser;
    }

    @Override
    public User DeleteUser(int index) {
        User returnUser = User.builder().build();
        for(User u : users) {
            if(u.getId() == index) {
                returnUser = u;
            }
        }
        users.remove(returnUser);
        return returnUser;
    }
}
