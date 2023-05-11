package com.example.demo.business.impl.gameorder;

import com.example.demo.business.cases.gameorder.GetGameOrdersByUserUseCase;
import com.example.demo.domain.GameOrder;
import com.example.demo.persistence.domain.persistenceClass.GameOrderPersistence;
import com.example.demo.persistence.repository.GameOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetGameOrdersByUserUseCaseImpl implements GetGameOrdersByUserUseCase {
    private final GameOrderRepository gameOrderRepository;

    /**
     *
     * @param userIndex
     * @return
     */
    @Override
    public List<GameOrder> getGameOrdersByUser(int userIndex) {
        List<GameOrderPersistence> list = gameOrderRepository.findAll();
        List<GameOrder> gameOrders = new ArrayList<>();
        for(GameOrderPersistence gop : list) {
            if(gop.getUser() == userIndex) {
                GameOrder gameOrder = GameOrder.builder()
                        .id(Math.toIntExact(gop.getId()))
                        .game(gop.getGame())
                        .user(gop.getUser())
                        .units(gop.getUnits())
                        .build();
                gameOrders.add(gameOrder);
            }
        }
        return gameOrders;
    }
}
