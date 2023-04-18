package com.example.demo.controller;

import com.example.demo.business.cases.gameOrders.AddGameOrderUseCase;
import com.example.demo.business.cases.gameOrders.GetGameOrderUseCase;
import com.example.demo.business.cases.gameOrders.GetGameOrdersByUserUseCase;
import com.example.demo.configuration.security.isauthenticated.IsAuthenticated;
import com.example.demo.domain.Addition;
import com.example.demo.domain.GameOrder;
import com.example.demo.domain.News;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:3000/", allowedHeaders = "*")
@RequestMapping("/gameOrders")
@RequiredArgsConstructor
public class GameOrderController {
    private final AddGameOrderUseCase addGameOrderUseCase;
    private final GetGameOrderUseCase getGameOrderUseCase;
    private final GetGameOrdersByUserUseCase getGameOrdersByUserUseCase;

    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @PostMapping("")
    public GameOrder AddGameOrder(@RequestBody @Valid GameOrder gameOrder) {
        return addGameOrderUseCase.AddGameOrder(gameOrder);
    }
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @GetMapping("/getByUser/{id}")
    public List<GameOrder> GetGameOrdersByUser(@PathVariable(value = "id") final int id) {
        return getGameOrdersByUserUseCase.GetGameOrdersByUser(id);
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @GetMapping("/{id}")
    public GameOrder GetGameOrder(@PathVariable(value = "id") final int id) {
        return getGameOrderUseCase.GetGameOrder(id);
    }
}
