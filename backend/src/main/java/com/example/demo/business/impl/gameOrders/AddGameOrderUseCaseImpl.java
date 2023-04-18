package com.example.demo.business.impl.gameOrders;

import com.example.demo.business.cases.gameOrders.AddGameOrderUseCase;
import com.example.demo.domain.GameOrder;
import com.example.demo.domain.persistenceClasses.GameOrderPersistence;
import com.example.demo.persistence.repositories.GameOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddGameOrderUseCaseImpl implements AddGameOrderUseCase {
    private final GameOrderRepository gameOrderRepository;

    @Override
    public GameOrder AddGameOrder(GameOrder gameOrder) {
        GameOrderPersistence gameOrderPersistence = GameOrderPersistence.builder()
                .game(gameOrder.getGame())
                .user(gameOrder.getUser())
                .units(gameOrder.getUnits())
                .build();
        gameOrderRepository.save(gameOrderPersistence);
        return gameOrder;
    }
}
