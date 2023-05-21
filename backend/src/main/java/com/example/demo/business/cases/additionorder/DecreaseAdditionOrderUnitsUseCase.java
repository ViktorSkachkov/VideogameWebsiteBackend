package com.example.demo.business.cases.additionorder;

import com.example.demo.domain.AdditionOrder;

public interface DecreaseAdditionOrderUnitsUseCase {
    AdditionOrder decreaseAdditionOrderUnits(Long additionOrderId);
}
