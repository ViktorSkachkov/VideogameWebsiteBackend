package com.example.demo.business.impl.gameorder;

import com.example.demo.business.cases.gameorder.GetGameOrdersRankedUseCase;
import com.example.demo.domain.GameOrder;
import com.example.demo.domain.RankingAdditionOrder;
import com.example.demo.domain.RankingGameOrder;
import com.example.demo.persistence.entity.GameOrderPersistence;
import com.example.demo.persistence.repository.GameOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetGameOrdersRankedUseCaseImpl implements GetGameOrdersRankedUseCase {
    private final GameOrderRepository gameOrderRepository;


    @Override
    public List<RankingGameOrder> getGameOrdersRanked() {
        List<GameOrderPersistence> list = gameOrderRepository.findAll();

        List<RankingGameOrder> rankingGameOrders = getRankings(list);

        return rankingGameOrders;
    }

    List<RankingGameOrder> getRankings(List<GameOrderPersistence> gameOrderPersistences) {
        List<RankingGameOrder> rankingGameOrders = new ArrayList<>();

        Long id = Long.valueOf(0);

        List<Integer> gameIds = new ArrayList<>();

        for(GameOrderPersistence aop : gameOrderPersistences) {

            if(!gameIds.contains(aop.getGame())) {
                id++;
                RankingGameOrder rankingGameOrder = RankingGameOrder.builder()
                        .id((long) aop.getId())
                        .totalPrice(0)
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
                rankingGameOrders.add(rankingGameOrder);
            }
            else {
                RankingGameOrder rankingGameOrder = RankingGameOrder.builder().build();

                List<GameOrder> gameOrders = new ArrayList<>();

                for(int i=0; i<rankingGameOrders.size(); i++) {
                    if(aop.getGame() == rankingGameOrders.get(i).getReviewed_item_id()) {
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
                        rankingGameOrders.set(i, rankingGameOrder);
                    }
                }

            }
        }

        return rankingGameOrders;
    }
}
