package com.example.demo.business.impl.additionorder;

import com.example.demo.business.cases.additionorder.GetAdditionOrdersRankedUseCase;
import com.example.demo.domain.AdditionOrder;
import com.example.demo.domain.RankingAdditionOrder;
import com.example.demo.persistence.entity.AdditionOrderPersistence;
import com.example.demo.persistence.repository.AdditionOrderRepository;
import com.example.demo.persistence.repository.AdditionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAdditionOrdersRankedUseCaseImpl implements GetAdditionOrdersRankedUseCase {
    private final AdditionOrderRepository additionOrderRepository;
    private final AdditionRepository additionRepository;

    @Override
    public List<RankingAdditionOrder> getAdditionOrdersRanked(int id) {

        LocalDateTime endDate = LocalDateTime.now();

        if (id == 0) {
            endDate = LocalDateTime.of(1970, 12, 18, 14, 30, 40);
        }
        if (id == 1) {
            endDate = LocalDateTime.now().minusMonths(1);
        }
        if (id == 6) {
            endDate = LocalDateTime.now().minusMonths(6);
        }
        if (id == 12) {
            endDate = LocalDateTime.now().minusMonths(12);
        }

        List<AdditionOrderPersistence> list = additionOrderRepository.findAll();

        List<RankingAdditionOrder> rankingAdditionOrders = getRankings(list, endDate);

        List<RankingAdditionOrder> rankingAdditionOrders2 = reverseOrder(rankingAdditionOrders);

        return rankingAdditionOrders2;
    }

    List<RankingAdditionOrder> getRankings(List<AdditionOrderPersistence> additionOrderPersistences,
                                           LocalDateTime endDate) {
        List<RankingAdditionOrder> rankingAdditionOrders = new ArrayList<>();

        Long id = Long.valueOf(0);

        List<Integer> additionIds = new ArrayList<>();

        for (AdditionOrderPersistence aop : additionOrderPersistences) {

            String name = additionRepository.findNameById((long) aop.getAddition());
            double price = additionRepository.findPriceById((long) aop.getAddition());

            if (!additionIds.contains(aop.getAddition()) && aop.getTime().isAfter(endDate)
                    && aop.getApproved()) {
                id++;
                RankingAdditionOrder rankingAdditionOrder = RankingAdditionOrder.builder()
                        .id((long) aop.getId())
                        .price(price)
                        .name(name)
                        .reviewedItemId(aop.getAddition())
                        .additionOrderList(List.of(AdditionOrder.builder()
                                .time(aop.getTime())
                                .user(aop.getUser())
                                .addition(aop.getAddition())
                                .units(aop.getUnits())
                                .build()))
                        .build();
                additionIds.add(aop.getAddition());
                rankingAdditionOrder.calculateNumberOfTimesBought();
                rankingAdditionOrder.calculateTotalPrice();
                rankingAdditionOrders.add(rankingAdditionOrder);
            } else {
                RankingAdditionOrder rankingAdditionOrder = RankingAdditionOrder.builder().build();

                List<AdditionOrder> additionOrders = new ArrayList<>();

                for (int i = 0; i < rankingAdditionOrders.size(); i++) {
                    if (aop.getAddition() == rankingAdditionOrders.get(i).getReviewedItemId()
                            && aop.getTime().isAfter(endDate) && aop.getApproved()) {
                        rankingAdditionOrder = rankingAdditionOrders.get(i);
                        for (AdditionOrder ao : rankingAdditionOrder.getAdditionOrderList()) {
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
                        rankingAdditionOrder.calculateTotalPrice();
                        rankingAdditionOrders.set(i, rankingAdditionOrder);
                    }
                }

            }
        }

        return rankingAdditionOrders;
    }

    @Override
    public List<RankingAdditionOrder> reverseOrder(List<RankingAdditionOrder> rankingAdditionOrders) {

        Collections.sort(rankingAdditionOrders, new Comparator<RankingAdditionOrder>() {
            public int compare(RankingAdditionOrder c1, RankingAdditionOrder c2) {
                if (c1.getTotalPrice() > c2.getTotalPrice()) return -1;
                if (c1.getTotalPrice() < c2.getTotalPrice()) return 1;
                return 0;
            }
        });

        return rankingAdditionOrders;
    }
}
