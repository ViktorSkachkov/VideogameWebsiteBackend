package com.example.demo.controller;

import com.example.demo.business.cases.gameorder.AddGameOrderUseCase;
import com.example.demo.business.cases.gameorder.GetGameOrderUseCase;
import com.example.demo.business.cases.gameorder.GetGameOrdersByUserUseCase;
import com.example.demo.configuration.security.isauthenticated.IsAuthenticated;
import com.example.demo.domain.GameOrder;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*")
@RequestMapping("/gameOrders")
@RequiredArgsConstructor
public class GameOrderController {
    private final AddGameOrderUseCase addGameOrderUseCase;
    private final GetGameOrderUseCase getGameOrderUseCase;
    private final GetGameOrdersByUserUseCase getGameOrdersByUserUseCase;

    /**
     * @param gameOrder
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @PostMapping("")
    public GameOrder addGameOrder(@RequestBody @Valid GameOrder gameOrder) {
        return addGameOrderUseCase.addGameOrder(gameOrder);
    }

    /**
     * @param id
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @GetMapping("/getByUser/{id}")
    public List<GameOrder> getGameOrdersByUser(@PathVariable(value = "id") final int id) {
        return getGameOrdersByUserUseCase.getGameOrdersByUser(id);
    }

    /**
     * @param id
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @GetMapping("/{id}")
    public GameOrder getGameOrder(@PathVariable(value = "id") final int id) {
        return getGameOrderUseCase.getGameOrder(id);
    }
}
