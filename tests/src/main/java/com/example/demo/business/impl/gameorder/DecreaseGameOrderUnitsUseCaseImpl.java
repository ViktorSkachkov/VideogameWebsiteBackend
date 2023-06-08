package com.example.demo.business.impl.gameorder;

import com.example.demo.business.cases.gameorder.DecreaseGameOrderUnitsUseCase;
import com.example.demo.domain.GameOrder;
import com.example.demo.exception.IsEmptyException;
import com.example.demo.persistence.entity.GameOrderPersistence;
import com.example.demo.persistence.repository.GameOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DecreaseGameOrderUnitsUseCaseImpl implements DecreaseGameOrderUnitsUseCase {
    private final GameOrderRepository gameOrderRepository;

    /**
     * @param gameOrderId
     * @return
     */
    @Override
    public GameOrder decreaseGameOrderUnits(int gameOrderId) {
        Optional<GameOrderPersistence> gameOrder = gameOrderRepository.findById((long) gameOrderId);
        if(gameOrder.isEmpty()) {
            throw new IsEmptyException();
        }

        if(gameOrder.isPresent()) {
            int units = gameOrder.get().getUnits();
            double totalPrice = gameOrder.get().getTotalPrice();
            double price = totalPrice / units;
            units -= 1;
            gameOrder.get().setUnits(units);
            totalPrice -= price;
            gameOrder.get().setTotalPrice(totalPrice);


            gameOrderRepository.save(gameOrder.get());
        }

        return GameOrder.builder()
                .id(gameOrder.get().getId())
                .game(gameOrder.get().getGame())
                .time(gameOrder.get().getTime())
                .user(gameOrder.get().getUser())
                .approved(gameOrder.get().getApproved())
                .units(gameOrder.get().getUnits())
                .build();
    }
}
