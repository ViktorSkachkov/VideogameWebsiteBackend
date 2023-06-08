package com.example.demo.business.cases.gameorder;

import com.example.demo.domain.GameOrder;

public interface ConfirmGameOrderUseCase {
    GameOrder confirmGameOrder(int userId);
}
