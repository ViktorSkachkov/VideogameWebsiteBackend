package com.example.demo.business.impl.gameorder;

import com.example.demo.business.cases.gameorder.AddGameOrderUseCase;
import com.example.demo.domain.GameOrder;
import com.example.demo.persistence.entity.GameOrderPersistence;
import com.example.demo.persistence.repository.GameOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AddGameOrderUseCaseImpl implements AddGameOrderUseCase {
    private final GameOrderRepository gameOrderRepository;

    /**
     * @param gameOrder
     * @return
     */
    @Override
    public GameOrder addGameOrder(GameOrder gameOrder) {
        GameOrderPersistence gameOrderPersistence = GameOrderPersistence.builder()
                .game(gameOrder.getGame())
                .user(gameOrder.getUser())
                .units(gameOrder.getUnits())
                .time(LocalDateTime.now())
                .build();
        gameOrderRepository.save(gameOrderPersistence);
        return gameOrder;
    }
}
