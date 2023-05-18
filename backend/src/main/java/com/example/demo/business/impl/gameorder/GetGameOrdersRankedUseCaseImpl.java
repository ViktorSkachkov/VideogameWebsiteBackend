package com.example.demo.business.impl.gameorder;

import com.example.demo.business.cases.gameorder.GetGameOrdersRankedUseCase;
import com.example.demo.domain.GameOrder;
import com.example.demo.domain.RankingAdditionOrder;
import com.example.demo.domain.RankingGameOrder;
import com.example.demo.domain.Videogame;
import com.example.demo.persistence.entity.GameOrderPersistence;
import com.example.demo.persistence.repository.GameOrderRepository;
import com.example.demo.persistence.repository.VideogameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetGameOrdersRankedUseCaseImpl implements GetGameOrdersRankedUseCase {
    private final GameOrderRepository gameOrderRepository;
    private final VideogameRepository videogameRepository;

    @Override
    public List<RankingGameOrder> getGameOrdersRanked(int id) {

        LocalDateTime endDate = LocalDateTime.now();

        if(id == 0) {
            endDate = LocalDateTime.of(1970, 12, 18, 14, 30, 40);
        }
        if(id == 1) {
            endDate = LocalDateTime.now().minusMonths(1);
        }
        if(id == 6) {
            endDate = LocalDateTime.now().minusMonths(6);
        }
        if(id == 12) {
            endDate = LocalDateTime.now().minusMonths(12);
        }

        List<GameOrderPersistence> list = gameOrderRepository.findAll();

        List<RankingGameOrder> rankingGameOrders = getRankings(list, endDate);

        List<RankingGameOrder> rankingGameOrders2 = reverseOrder(rankingGameOrders);

        return rankingGameOrders2;
    }

    List<RankingGameOrder> getRankings(List<GameOrderPersistence> gameOrderPersistences,
                                       LocalDateTime endDate) {
        List<RankingGameOrder> rankingGameOrders = new ArrayList<>();

        Long id = Long.valueOf(0);

        List<Integer> gameIds = new ArrayList<>();

        for(GameOrderPersistence aop : gameOrderPersistences) {

           String name = videogameRepository.findNameById((long) aop.getGame());
           double price = videogameRepository.findPriceById((long) aop.getGame());

            if(!gameIds.contains(aop.getGame()) && aop.getTime().isAfter(endDate) && aop.getApproved()) {
                id++;
                RankingGameOrder rankingGameOrder = RankingGameOrder.builder()
                        .id((long) aop.getId())
                        .price(price)
                        .name(name)
                        .reviewed_item_id(aop.getGame())
                        .gameOrderList(List.of(GameOrder.builder()
                                .time(aop.getTime())
                                .user(aop.getUser())
                                .game(aop.getGame())
                                .units(aop.getUnits())
                                .build()))
                        .build();
                gameIds.add(aop.getGame());
                rankingGameOrder.calculateNumberOfTimesBought();
                rankingGameOrder.calculateTotalPrice();
                rankingGameOrders.add(rankingGameOrder);
            }
            else {
                RankingGameOrder rankingGameOrder = RankingGameOrder.builder().build();

                List<GameOrder> gameOrders = new ArrayList<>();

                for(int i=0; i<rankingGameOrders.size(); i++) {
                    if(aop.getGame() == rankingGameOrders.get(i).getReviewed_item_id()
                            && aop.getTime().isAfter(endDate) && aop.getApproved()) {
                        rankingGameOrder = rankingGameOrders.get(i);
                        for(GameOrder ao : rankingGameOrder.getGameOrderList()) {
                            //additionOrders = rankingAdditionOrder.getAdditionOrderList();
                            gameOrders.add(ao);
                        }

                        gameOrders.add(GameOrder.builder()
                                .id(aop.getId())
                                .time(aop.getTime())
                                .user(aop.getUser())
                                .game(aop.getGame())
                                .units(aop.getUnits())
                                .build());

                        rankingGameOrder.setGameOrderList(gameOrders);

                        rankingGameOrder.calculateNumberOfTimesBought();
                        rankingGameOrder.calculateTotalPrice();
                        rankingGameOrders.set(i, rankingGameOrder);
                    }
                }

            }
        }

        return rankingGameOrders;
    }

    @Override
    public List<RankingGameOrder> reverseOrder(List<RankingGameOrder> rankingGameOrders) {

        Collections.sort(rankingGameOrders, new Comparator<RankingGameOrder>() {
            public int compare(RankingGameOrder c1, RankingGameOrder c2) {
                if (c1.getTotalPrice() > c2.getTotalPrice()) return -1;
                if (c1.getTotalPrice() < c2.getTotalPrice()) return 1;
                return 0;
            }});

        return rankingGameOrders;
    }
}
