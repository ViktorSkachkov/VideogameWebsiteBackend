package com.example.demo.business.impl.gameOrders;

import com.example.demo.business.cases.gameOrders.GetGameOrderUseCase;
import com.example.demo.domain.GameOrder;
import com.example.demo.domain.persistenceClasses.GameOrderPersistence;
import com.example.demo.persistence.repositories.GameOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetGameOrderUseCaseImpl implements GetGameOrderUseCase {
    private final GameOrderRepository gameOrderRepository;

    @Override
    public GameOrder GetGameOrder(int index) {
        Optional<GameOrderPersistence> gop = gameOrderRepository.findById(Long.valueOf(index));
        if(gop.isEmpty()) {

        }
        GameOrder gameOrder = GameOrder.builder()
                .id(Math.toIntExact(gop.get().getId()))
                .user(gop.get().getUser())
                .game(gop.get().getGame())
                .units(gop.get().getUnits())
                .build();
        return gameOrder;
    }
}
