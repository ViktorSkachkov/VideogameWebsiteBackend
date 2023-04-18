package com.example.demo.controller;

import com.example.demo.business.cases.users.*;
import com.example.demo.configuration.security.isauthenticated.IsAuthenticated;
import com.example.demo.domain.LoginResponse;
import com.example.demo.domain.User;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:3000/", allowedHeaders = "*")
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final GetUsersUseCase getUsersUseCase;
    private final GetUserUseCase getUserUseCase;
    private final AddUserUseCase addUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    /**
     *
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @GetMapping("")
    public List<User> GetUsers() {
        return getUsersUseCase.GetUsers();
    }

    /**
     *
     * @param id
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @GetMapping("/{id}")
    public User GetUser(@PathVariable(value = "id") final int id) {
        return getUserUseCase.GetUser(id);
    }

    /**
     *
     * @param user
     * @return
     */
    @PostMapping("")
    public User AddUser(@RequestBody @Valid User user) {
        return addUserUseCase.AddUser(user);
    }
    /*@PostMapping("")
    public ResponseEntity<LoginResponse> AddUser(@RequestBody @Valid User user) {
        return ResponseEntity.ok(addUserUseCase.AddUser(user));
    }*/

    /**
     *
     * @param id
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @DeleteMapping("/{id}")
    public User DeleteUser(@PathVariable(value = "id") final int id) {
        return deleteUserUseCase.DeleteUser(id);
    }

    /**
     *
     * @param user
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @PutMapping("")
    public LoginResponse UpdateUser(@RequestBody @Valid User user) {
        return updateUserUseCase.UpdateUser(user);
    }
}
