package com.example.demo.business.impl.gameorder;

import com.example.demo.business.cases.gameorder.DecreaseGameOrderUnitsUseCase;
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
    public int decreaseGameOrderUnits(int gameOrderId) {
        Optional<GameOrderPersistence> gameOrder = gameOrderRepository.findById((long) gameOrderId);
        if(gameOrder.isEmpty()) {

        }

        int units = gameOrder.get().getUnits();
        units -= 1;
        gameOrder.get().setUnits(units);

        gameOrderRepository.save(gameOrder.get());
        return 0;
    }
}
