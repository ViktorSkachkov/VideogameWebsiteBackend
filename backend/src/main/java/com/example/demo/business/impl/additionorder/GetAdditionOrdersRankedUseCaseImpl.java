package com.example.demo.business.impl.additionorder;

import com.example.demo.business.cases.additionorder.GetAdditionOrdersRankedUseCase;
import com.example.demo.domain.AdditionOrder;
import com.example.demo.domain.RankingAdditionOrder;
import com.example.demo.persistence.entity.AdditionOrderPersistence;
import com.example.demo.persistence.repository.AdditionOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAdditionOrdersRankedUseCaseImpl implements GetAdditionOrdersRankedUseCase {
    private final AdditionOrderRepository additionOrderRepository;
    //private final AdditionRepository additionRepository;

    @Override
    public List<RankingAdditionOrder> getAdditionOrdersRanked(/*LocalDateTime startDate, LocalDateTime endDate*/) {
        List<AdditionOrderPersistence> list = additionOrderRepository.findAll();

        List<RankingAdditionOrder> rankingAdditionOrders = getRankings(list);

        return rankingAdditionOrders;
    }

    List<RankingAdditionOrder> getRankings(List<AdditionOrderPersistence> additionOrderPersistences) {
        List<RankingAdditionOrder> rankingAdditionOrders = new ArrayList<>();

        Long id = Long.valueOf(0);

        List<Integer> additionIds = new ArrayList<>();

        for(AdditionOrderPersistence aop : additionOrderPersistences) {

            if(!additionIds.contains(aop.getAddition())) {
                id++;
                RankingAdditionOrder rankingAdditionOrder = RankingAdditionOrder.builder()
                        .id((long) aop.getId())
                        .totalPrice(0)
                        .reviewed_item_id(aop.getAddition())
                        .additionOrderList(List.of(AdditionOrder.builder()
                                        .time(aop.getTime())
                                        .user(aop.getUser())
                                        .addition(aop.getAddition())
                                        .units(aop.getUnits())
                                .build()))
                        .build();
                additionIds.add(aop.getAddition());
                rankingAdditionOrder.calculateNumberOfTimesBought();
                rankingAdditionOrders.add(rankingAdditionOrder);
            }
            else {
                RankingAdditionOrder rankingAdditionOrder = RankingAdditionOrder.builder().build();

                List<AdditionOrder> additionOrders = new ArrayList<>();

                for(int i=0; i<rankingAdditionOrders.size(); i++) {
                    if(aop.getAddition() == rankingAdditionOrders.get(i).getReviewed_item_id()) {
                        rankingAdditionOrder = rankingAdditionOrders.get(i);
                        for(AdditionOrder ao : rankingAdditionOrder.getAdditionOrderList()) {
                            //additionOrders = rankingAdditionOrder.getAdditionOrderList();
                            additionOrders.add(ao);
                        }

                        additionOrders.add(AdditionOrder.builder()
                                        .id(aop.getId())
                                .time(aop.getTime())
                                .user(aop.getUser())
                                .addition(aop.getAddition())
                                .units(aop.getUnits())
                                .build());

                        rankingAdditionOrder.setAdditionOrderList(additionOrders);

                        rankingAdditionOrder.calculateNumberOfTimesBought();
                        rankingAdditionOrders.set(i, rankingAdditionOrder);
                    }
                }

            }
        }

        return rankingAdditionOrders;
    }
}
