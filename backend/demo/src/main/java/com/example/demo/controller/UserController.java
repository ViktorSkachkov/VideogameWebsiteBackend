package com.example.demo.controller;

import com.example.demo.business.cases.users.*;
import com.example.demo.domain.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins="*", allowedHeaders = "*")
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final GetUsersUseCase getUsersUseCase;
    private final GetUserUseCase getUserUseCase;
    private final AddUserUseCase addUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    @GetMapping("")
    public List<User> GetUsers() {
        return getUsersUseCase.GetUsers();
    }
    @GetMapping("/{id}")
    public User GetUser(@PathVariable(value = "id") final int id) {
        return getUserUseCase.GetUser(id);
    }
    @PostMapping("")
    public User AddUser(@RequestBody @Valid User user) {
        return addUserUseCase.AddUser(user);
    }
    @DeleteMapping("/{id}")
    public User DeleteUser(@PathVariable(value = "id") final int id) {
        return deleteUserUseCase.DeleteUser(id);
    }
    @PutMapping("")
    public User UpdateUser(@RequestBody @Valid User user) {
        return updateUserUseCase.UpdateUser(user);
    }
}
