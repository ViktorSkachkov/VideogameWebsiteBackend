package com.example.demo.business.impl.gameorder;

import com.example.demo.business.cases.gameorder.GetGameOrderUseCase;
import com.example.demo.domain.GameOrder;
import com.example.demo.persistence.entity.GameOrderPersistence;
import com.example.demo.persistence.repository.GameOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetGameOrderUseCaseImpl implements GetGameOrderUseCase {
    private final GameOrderRepository gameOrderRepository;

    /**
     * @param index
     * @return
     */
    @Override
    public GameOrder getGameOrder(int index) {
        Optional<GameOrderPersistence> gop = gameOrderRepository.findById(Long.valueOf(index));
        if (gop.isEmpty()) {

        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDateTime = gop.get().getTime().format(dateTimeFormatter);

        GameOrder gameOrder = GameOrder.builder()
                .id(Math.toIntExact(gop.get().getId()))
                .user(gop.get().getUser())
                .game(gop.get().getGame())
                .units(gop.get().getUnits())
                .time(gop.get().getTime())
                .dateFormatted(formattedDateTime)
                .build();
        return gameOrder;
    }
}
