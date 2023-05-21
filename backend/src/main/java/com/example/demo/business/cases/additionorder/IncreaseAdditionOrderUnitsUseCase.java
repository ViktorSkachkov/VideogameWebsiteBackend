package com.example.demo.business.cases.additionorder;

import com.example.demo.domain.AdditionOrder;

public interface IncreaseAdditionOrderUnitsUseCase {
    AdditionOrder increaseAdditionOrderUnits(Long additionOrderId);
}
