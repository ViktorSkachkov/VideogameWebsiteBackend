package com.example.demo.business.impl.gameorder;

import com.example.demo.business.cases.gameorder.DeleteGameOrderUseCase;
import com.example.demo.domain.GameOrder;
import com.example.demo.persistence.repository.GameOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteGameOrderUseCaseImpl implements DeleteGameOrderUseCase {
    private final GameOrderRepository gameOrderRepository;

    /**
     * @param gameOrderId
     * @return
     */
    @Override
    public GameOrder deleteGame(int gameOrderId) {
        gameOrderRepository.deleteById((long) gameOrderId);
        return GameOrder.builder()
                .id(gameOrderId)
                .build();
    }
}
