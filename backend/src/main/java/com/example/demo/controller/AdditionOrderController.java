package com.example.demo.controller;

import com.example.demo.business.cases.additionorder.AddAdditionOrderUseCase;
import com.example.demo.business.cases.additionorder.GetAdditionOrderUseCase;
import com.example.demo.business.cases.additionorder.GetAdditionOrdersByUserUseCase;
import com.example.demo.business.cases.additionorder.GetAdditionOrdersRankedUseCase;
import com.example.demo.configuration.security.isauthenticated.IsAuthenticated;
import com.example.demo.domain.AdditionOrder;
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
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @GetMapping("/ranked/{id}")
    public List<RankingAdditionOrder> getAdditionOrdersRanked(@PathVariable(value = "id") final int id) {
        return getAdditionOrdersRankedUseCase.getAdditionOrdersRanked(id);
    }
}
