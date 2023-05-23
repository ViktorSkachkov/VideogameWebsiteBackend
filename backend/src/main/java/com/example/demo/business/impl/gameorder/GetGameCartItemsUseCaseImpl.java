package com.example.demo.business.impl.gameorder;

import com.example.demo.business.cases.gameorder.GetGameCartItemsUseCase;
import com.example.demo.domain.GameOrder;
import com.example.demo.persistence.entity.GameOrderPersistence;
import com.example.demo.persistence.repository.GameOrderRepository;
import com.example.demo.persistence.repository.VideogameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetGameCartItemsUseCaseImpl implements GetGameCartItemsUseCase {
    private final GameOrderRepository gameOrderRepository;
    private final VideogameRepository videogameRepository;

    /**
     * @param userIndex
     * @return
     */
    @Override
    public List<GameOrder> getGameCartItems(int userIndex) {
        List<GameOrderPersistence> list = gameOrderRepository.findAll();
        List<GameOrder> gameOrders = new ArrayList<>();
        
        list.forEach(gop -> {
            if (gop.getUser() == userIndex && !gop.getApproved()) {

                double price = videogameRepository.findPriceById((long) gop.getGame());
                double totalPrice = price * gop.getUnits();

                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String formattedDateTime = gop.getTime().format(dateTimeFormatter);

                GameOrder gameOrder = GameOrder.builder()
                        .id(Math.toIntExact(gop.getId()))
                        .game(gop.getGame())
                        .user(gop.getUser())
                        .units(gop.getUnits())
                        .time(gop.getTime())
                        .dateFormatted(formattedDateTime)
                        .approved(gop.getApproved())
                        .totalPrice(totalPrice)
                        .build();
                gameOrders.add(gameOrder);
            }
        });

        List<GameOrder> newList = reverseOrder(gameOrders);
        return newList;
    }

    /**
     * @param gameOrders
     * @return
     */
    @Override
    public List<GameOrder> reverseOrder(List<GameOrder> gameOrders) {
        List<GameOrder> result = new ArrayList<>();

        for (int i = gameOrders.size() - 1; i >= 0; i--) {
            result.add(gameOrders.get(i));
        }

        return result;
    }
}
