package com.example.demo.business.impl.gameorder;

import com.example.demo.business.cases.gameorder.AddGameOrderUseCase;
import com.example.demo.domain.GameOrder;
import com.example.demo.persistence.entity.GameOrderPersistence;
import com.example.demo.persistence.repository.GameOrderRepository;
import com.example.demo.persistence.repository.VideogameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddGameOrderUseCaseImpl implements AddGameOrderUseCase {
    private final GameOrderRepository gameOrderRepository;
    private final VideogameRepository videogameRepository;

    /**
     * @param gameOrder
     * @return
     */
    @Override
    public GameOrder addGameOrder(GameOrder gameOrder) {
        List<GameOrderPersistence> gameOrderPersistenceList = gameOrderRepository.findCartItemsByUserId((long) gameOrder.getUser(), false);

        for(GameOrderPersistence element : gameOrderPersistenceList) {
            if(gameOrder.getGame() == element.getGame()) {
                int units = element.getUnits() + gameOrder.getUnits();
                double totalPrice = element.getTotalPrice();
                double price = totalPrice / element.getUnits();
                element.setUnits(units);
                element.setTotalPrice(price * units);
                gameOrderRepository.save(element);
                return gameOrder;
            }
        }

        double price = videogameRepository.findPriceById((long) gameOrder.getGame());

        GameOrderPersistence gameOrderPersistence = GameOrderPersistence.builder()
                .game(gameOrder.getGame())
                .user(gameOrder.getUser())
                .units(gameOrder.getUnits())
                .time(LocalDateTime.now())
                .approved(false)
                .totalPrice(price * gameOrder.getUnits())
                .build();
        gameOrderRepository.save(gameOrderPersistence);
        return gameOrder;
    }
}
