package com.example.demo.business.cases.gameorder;

import com.example.demo.domain.GameOrder;

public interface DecreaseGameOrderUnitsUseCase {
    GameOrder decreaseGameOrderUnits(int gameOrderId);
}
