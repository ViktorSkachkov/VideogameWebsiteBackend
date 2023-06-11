package com.example.demo.controller;

import com.example.demo.business.cases.gameorder.DecreaseGameOrderUnitsUseCase;
import com.example.demo.business.cases.gameorder.IncreaseGameOrderUnitsUseCase;
import com.example.demo.business.cases.gameorder.*;
import com.example.demo.configuration.security.isauthenticated.IsAuthenticated;
import com.example.demo.domain.GameOrder;
import com.example.demo.domain.RankedItem;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3090/", "http://localhost:3000/"}, allowedHeaders = "*")
@RequestMapping("/gameOrders")
@RequiredArgsConstructor
public class GameOrderController {
    private final AddGameOrderUseCase addGameOrderUseCase;
    private final GetGameOrderUseCase getGameOrderUseCase;
    private final GetGameOrdersByUserUseCase getGameOrdersByUserUseCase;
    private final GetGameOrdersRankedUseCase getGameOrdersRankedUseCase;
    private final GetGameCartItemsUseCase getGameCartItemsUseCase;
    private final ConfirmGameOrderUseCase confirmGameOrderUseCase;
    private final DeleteGameOrderUseCase deleteGameOrderUseCase;
    private final DecreaseGameOrderUnitsUseCase decreaseGameOrderUnitsUseCase;
    private final IncreaseGameOrderUnitsUseCase increaseGameOrderUnitsUseCase;

    /**
     * @param gameOrder
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @PostMapping("")
    public GameOrder addGameOrder(@RequestBody @Valid GameOrder gameOrder) {
        int i;
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
        int i;
        return getGameOrdersByUserUseCase.getGameOrdersByUser(id);
    }

    /**
     * @param id
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE"})
    @GetMapping("/{id}")
    public GameOrder getGameOrder(@PathVariable(value = "id") final int id) {
        return getGameOrderUseCase.getGameOrder(id);
    }

    /**
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE"})
    @GetMapping("/ranked/{id}")
    public List<RankedItem> getAdditionOrdersRanked(@PathVariable(value = "id") final int id) {
        LocalDateTime endDate = LocalDateTime.now().plusDays(1);
        return getGameOrdersRankedUseCase.getGameOrdersRanked(id, endDate);
    }

    /**
     * @param id
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_CUSTOMER"})
    @GetMapping("/cart/{id}")
    public List<GameOrder> getGameCartItems(@PathVariable(value = "id") final int id) {
        return getGameCartItemsUseCase.getGameCartItems(id);
    }

    /**
     * @param id
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @PutMapping("/{id}")
    public GameOrder confirmAdditionOrders(@PathVariable(value = "id") final int id) {
        return confirmGameOrderUseCase.confirmGameOrder(id);
    }

    /**
     * @param id
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @DeleteMapping("/{id}")
    public GameOrder deleteGameOrder(@PathVariable(value = "id") final int id) {
        return deleteGameOrderUseCase.deleteGame(id);
    }

    /**
     * @param id
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @PutMapping("/increase/{id}")
    public GameOrder increaseGameOrderUnits(@PathVariable(value = "id") final int id) {
        return increaseGameOrderUnitsUseCase.increaseGameOrderUnits(id);
    }

    /**
     * @param id
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @PutMapping("/decrease/{id}")
    public GameOrder decreaseGameOrderUnits(@PathVariable(value = "id") final int id) {
        return decreaseGameOrderUnitsUseCase.decreaseGameOrderUnits(id);
    }
}
