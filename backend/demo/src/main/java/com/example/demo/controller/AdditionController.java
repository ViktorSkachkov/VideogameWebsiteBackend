package com.example.demo.controller;

import com.example.demo.business.cases.additions.*;
import com.example.demo.domain.Addition;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*", allowedHeaders = "*")
@RequestMapping("/additions")
@RequiredArgsConstructor
public class AdditionController {
    private final GetAdditionsUseCase getAdditionsUseCase;
    private final GetAdditionUseCase getAdditionUseCase;
    private final AddAdditionUseCase addAdditionUseCase;
    private final DeleteAdditionUseCase deleteAdditionUseCase;
    private final UpdateAdditionUseCase updateAdditionUseCase;

    @GetMapping("")
    public List<Addition> GetAdditions() {
        return getAdditionsUseCase.GetAdditions();
    }
    @GetMapping("/{id}")
    public Addition GetAddition(@PathVariable(value = "id") final int id) {
        return getAdditionUseCase.GetAddition(id);
    }
    @PostMapping("")
    public Addition AddAddition(@RequestBody @Valid Addition addition) {
        return addAdditionUseCase.AddAddition(addition);
    }
    @PutMapping("")
    public Addition UpdateAddition(@RequestBody @Valid Addition addition) {
        return updateAdditionUseCase.UpdateAddition(addition);
    }
    @DeleteMapping("/{id}")
    public Addition DeleteAddition(@PathVariable(value = "id") final int id) {
        return deleteAdditionUseCase.DeleteAddition(id);
    }
}
