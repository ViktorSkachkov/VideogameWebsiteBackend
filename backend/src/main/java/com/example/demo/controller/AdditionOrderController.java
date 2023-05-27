package com.example.demo.controller;

import com.example.demo.business.cases.additionorder.*;
import com.example.demo.configuration.security.isauthenticated.IsAuthenticated;
import com.example.demo.domain.AdditionOrder;
import com.example.demo.domain.RankedClass;
import com.example.demo.domain.RankingAdditionOrder;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*")
@RequestMapping("/additionOrders")
@RequiredArgsConstructor
public class AdditionOrderController {
    private final AddAdditionOrderUseCase addAdditionOrderUseCase;
    private final GetAdditionOrdersByUserUseCase getAdditionOrdersByUserUseCase;
    private final GetAdditionOrderUseCase getAdditionOrderUseCase;
    private final GetAdditionOrdersRankedUseCase getAdditionOrdersRankedUseCase;
    private final GetAdditionCartItemsUseCase getAdditionCartItemsUseCase;
    private final ConfirmAdditionOrderUseCase confirmAdditionOrderUseCase;
    private final DeleteAdditionOrderUseCase deleteAdditionOrderUseCase;
    private final DecreaseAdditionOrderUnitsUseCase decreaseAdditionOrderUnitsUseCase;
    private final IncreaseAdditionOrderUnitsUseCase increaseAdditionOrderUnitsUseCase;

    /**
     * @param additionOrder
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @PostMapping("")
    public AdditionOrder addAdditionOrder(@RequestBody @Valid AdditionOrder additionOrder) {
        return addAdditionOrderUseCase.addAdditionOrder(additionOrder);
    }

    /**
     * @param id
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @GetMapping("/getByUser/{id}")
    public List<AdditionOrder> getAdditionOrdersByUser(@PathVariable(value = "id") final int id) {
        return getAdditionOrdersByUserUseCase.getAdditionOrdersByUser(id);
    }

    /**
     * @param id
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @GetMapping("/{id}")
    public AdditionOrder getAdditionOrder(@PathVariable(value = "id") final int id) {
        return getAdditionOrderUseCase.getAdditionOrder(id);
    }

    /**
     * @return
     */
    /*@IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @GetMapping("/ranked/{id}")
    public List<RankingAdditionOrder> getAdditionOrdersRanked(@PathVariable(value = "id") final int id) {
        return getAdditionOrdersRankedUseCase.getAdditionOrdersRanked(id);
    }*/
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @GetMapping("/ranked/{id}")
    public List<RankedClass> getAdditionOrdersRanked(@PathVariable(value = "id") final int id) {
        return getAdditionOrdersRankedUseCase.getAdditionOrdersRanked(id);
    }

    /**
     * @param id
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_CUSTOMER"})
    @GetMapping("/cart/{id}")
    public List<AdditionOrder> getAdditionCartItems(@PathVariable(value = "id") final int id) {
        return getAdditionCartItemsUseCase.getAdditionCartItems(id);
    }

    /**
     * @param id
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @PutMapping("/{id}")
    public AdditionOrder confirmAdditionOrders(@PathVariable(value = "id") final int id) {
        return confirmAdditionOrderUseCase.confirmAdditionOrder(id);
    }

    /**
     * @param id
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @DeleteMapping("/{id}")
    public AdditionOrder deleteAdditionOrder(@PathVariable(value = "id") final int id) {
        return deleteAdditionOrderUseCase.deleteAddition(id);
    }

    /**
     * @param id
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @PutMapping("/increase/{id}")
    public AdditionOrder increaseAdditionOrderUnits(@PathVariable(value = "id") final Long id) {
        return increaseAdditionOrderUnitsUseCase.increaseAdditionOrderUnits(id);
    }

    /**
     * @param id
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @PutMapping("/decrease/{id}")
    public AdditionOrder decreaseAdditionOrderUnits(@PathVariable(value = "id") final Long id) {
        return decreaseAdditionOrderUnitsUseCase.decreaseAdditionOrderUnits(id);
    }
}
