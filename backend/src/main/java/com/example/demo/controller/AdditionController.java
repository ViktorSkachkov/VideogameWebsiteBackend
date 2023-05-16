package com.example.demo.controller;

import com.example.demo.business.cases.addition.*;
import com.example.demo.configuration.security.isauthenticated.IsAuthenticated;
import com.example.demo.domain.Addition;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*")
@RequestMapping("/additions")
@RequiredArgsConstructor
public class AdditionController {
    private final GetAdditionsUseCase getAdditionsUseCase;
    private final GetAdditionsByGameUseCase getAdditionsByGameUseCase;
    private final GetAdditionUseCase getAdditionUseCase;
    private final AddAdditionUseCase addAdditionUseCase;
    private final DeleteAdditionUseCase deleteAdditionUseCase;
    private final UpdateAdditionUseCase updateAdditionUseCase;

    /**
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @GetMapping("")
    public List<Addition> getAdditions() {
        return getAdditionsUseCase.getAdditions();
    }

    /**
     * @param id
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @GetMapping("/{id}")
    public Addition getAddition(@PathVariable(value = "id") final int id) {
        return getAdditionUseCase.getAddition(id);
    }

    /**
     * @param id
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @GetMapping("/getByGame/{id}")
    public List<Addition> getAdditionsByGame(@PathVariable(value = "id") final int id) {
        return getAdditionsByGameUseCase.getAdditionsByGame(id);
    }

    /**
     * @param addition
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE"})
    @PostMapping("")
    public Addition addAddition(@RequestBody @Valid Addition addition) {
        return addAdditionUseCase.addAddition(addition);
    }

    /**
     * @param addition
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE"})
    @PutMapping("")
    public Addition updateAddition(@RequestBody @Valid Addition addition) {
        return updateAdditionUseCase.updateAddition(addition);
    }

    /**
     * @param id
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE"})
    @DeleteMapping("/{id}")
    public Addition deleteAddition(@PathVariable(value = "id") final int id) {
        return deleteAdditionUseCase.deleteAddition(id);
    }
}