package com.example.demo.business.impl.gameorder;

import com.example.demo.business.cases.gameorder.IncreaseGameOrderUnitsUseCase;
import com.example.demo.domain.GameOrder;
import com.example.demo.exception.IsEmptyException;
import com.example.demo.persistence.entity.GameOrderPersistence;
import com.example.demo.persistence.repository.GameOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IncreaseGameOrderUnitsUseCaseImpl implements IncreaseGameOrderUnitsUseCase {
    private final GameOrderRepository gameOrderRepository;

    /**
     * @param gameOrderId
     * @return
     */
    @Override
    public GameOrder increaseGameOrderUnits(int gameOrderId) {
        Optional<GameOrderPersistence> gameOrder = gameOrderRepository.findById((long) gameOrderId);
        if(gameOrder.isEmpty()) {
            throw new IsEmptyException();
        }

        if(gameOrder.isPresent()) {
            int units = gameOrder.get().getUnits();
            units += 1;
            gameOrder.get().setUnits(units);

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
