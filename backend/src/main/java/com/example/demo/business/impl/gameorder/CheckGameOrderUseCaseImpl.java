package com.example.demo.business.impl.gameorder;

import com.example.demo.business.cases.gameorder.CheckGameOrderUseCase;
import com.example.demo.persistence.entity.GameOrderPersistence;
import com.example.demo.persistence.repository.GameOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckGameOrderUseCaseImpl implements CheckGameOrderUseCase {
    private final GameOrderRepository gameOrderRepository;

    @Override
    public boolean CheckGameOrder(int id) {
        List<GameOrderPersistence> list = gameOrderRepository.findCartItemsByUserId(Long.valueOf(id), false);
        if(list.size() > 0) {
            return true;
        }
        else {
            return false;
        }
    }
}
