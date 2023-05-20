package com.example.demo.business.impl.gameorder;

import com.example.demo.business.cases.gameorder.ConfirmGameOrderUseCase;
import com.example.demo.domain.GameOrder;
import com.example.demo.persistence.entity.GameOrderPersistence;
import com.example.demo.persistence.repository.GameOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ConfirmGameOrderUseCaseImpl implements ConfirmGameOrderUseCase {
    private final GameOrderRepository gameOrderRepository;

    /**
     * @param userId
     * @return
     */
    @Override
    public int confirmGameOrder(int userId) {
        List<GameOrderPersistence> list = gameOrderRepository.findByUserId(Long.valueOf(userId));
        LocalDateTime time = LocalDateTime.now();

        for (GameOrderPersistence gop : list) {
            if(!gop.getApproved()) {
                gop.setApproved(true);
                gop.setTime(time);
                gameOrderRepository.save(gop);
            }
        }
        return userId;
    }
}
