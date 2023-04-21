package com.example.demo.controller;

import com.example.demo.business.cases.additionOrders.AddAdditionOrderUseCase;
import com.example.demo.business.cases.additionOrders.GetAdditionOrderUseCase;
import com.example.demo.business.cases.additionOrders.GetAdditionOrdersByUserUseCase;
import com.example.demo.configuration.security.isauthenticated.IsAuthenticated;
import com.example.demo.domain.AdditionOrder;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:3000/", allowedHeaders = "*")
@RequestMapping("/additionOrders")
@RequiredArgsConstructor
public class AdditionOrderController {
    private final AddAdditionOrderUseCase addAdditionOrderUseCase;
    private final GetAdditionOrdersByUserUseCase getAdditionOrdersByUserUseCase;
    private final GetAdditionOrderUseCase getAdditionOrderUseCase;

    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @PostMapping("")
    public AdditionOrder AddAdditionOrder(@RequestBody @Valid AdditionOrder additionOrder) {
        return addAdditionOrderUseCase.AddAdditionOrder(additionOrder);
    }
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @GetMapping("/getByUser/{id}")
    public List<AdditionOrder> GetAdditionOrdersByUser(@PathVariable(value = "id") final int id) {
        return getAdditionOrdersByUserUseCase.GetAdditionOrdersByUser(id);
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @GetMapping("/{id}")
    public AdditionOrder GetAdditionOrder(@PathVariable(value = "id") final int id) {
        return getAdditionOrderUseCase.GetAdditionOrder(id);
    }
}
