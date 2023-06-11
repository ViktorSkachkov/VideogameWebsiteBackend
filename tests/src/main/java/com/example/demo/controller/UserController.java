package com.example.demo.controller;

import com.example.demo.business.cases.user.*;
import com.example.demo.configuration.security.isauthenticated.IsAuthenticated;
import com.example.demo.domain.LoginResponse;
import com.example.demo.domain.User;
import com.example.demo.domain.ValidationResponse;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3090/", "http://localhost:3000/"}, allowedHeaders = "*")
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final GetUsersUseCase getUsersUseCase;
    private final GetUserUseCase getUserUseCase;
    private final AddUserUseCase addUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final ValidateUsernameUseCase validateUsernameUseCase;
    private final ValidatePasswordUseCase validatePasswordUseCase;

    /**
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @GetMapping("")
    public List<User> getUsers() {
        return getUsersUseCase.getUsers();
    }

    /**
     * @param id
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @GetMapping("/{id}")
    public User getUser(@PathVariable(value = "id") final int id) {
        return getUserUseCase.getUser(id);
    }

    /**
     * @param user
     * @return
     */
    @PostMapping("")
    public User addUser(@RequestBody @Valid User user) {
        return addUserUseCase.addUser(user);
    }

    /**
     * @param id
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable(value = "id") final int id) {
        return deleteUserUseCase.deleteUser(id);
    }

    /**
     * @param user
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @PutMapping("")
    public LoginResponse updateUser(@RequestBody @Valid User user) {
        return updateUserUseCase.updateUser(user);
    }

    @GetMapping("/validatePassword/{password}")
    public ValidationResponse validatePassword(@PathVariable(value = "password") final String password) {
        return validatePasswordUseCase.validatePassword(password);
    }

    @GetMapping("/validateUsername/{username}")
    public ValidationResponse validateUsername(@PathVariable(value = "username") final String username) {
        return validateUsernameUseCase.validateUsername(username);
    }
}
