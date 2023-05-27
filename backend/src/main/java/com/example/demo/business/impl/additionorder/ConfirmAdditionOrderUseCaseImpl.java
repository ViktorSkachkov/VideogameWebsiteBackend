package com.example.demo.business.impl.additionorder;

import com.example.demo.business.cases.additionorder.ConfirmAdditionOrderUseCase;
import com.example.demo.domain.AdditionOrder;
import com.example.demo.domain.RankingAdditionOrder;
import com.example.demo.persistence.entity.AdditionOrderPersistence;
import com.example.demo.persistence.entity.GameOrderPersistence;
import com.example.demo.persistence.entity.RankingAdditionOrderPersistence;
import com.example.demo.persistence.repository.AdditionOrderRepository;
import com.example.demo.persistence.repository.AdditionRepository;
//import com.example.demo.persistence.repository.RankingAdditionOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConfirmAdditionOrderUseCaseImpl implements ConfirmAdditionOrderUseCase {
    private final AdditionOrderRepository additionOrderRepository;
    private final AdditionRepository additionRepository;
   // private final RankingAdditionOrderRepository rankingAdditionOrderRepository;

    /**
     * @param userId
     * @return
     */
    @Override
    public AdditionOrder confirmAdditionOrder(int userId) {
        List<AdditionOrderPersistence> list = additionOrderRepository.findByUserId(Long.valueOf(userId));
        LocalDateTime time = LocalDateTime.now();

        AdditionOrder returnResult = null;

        for (AdditionOrderPersistence aop : list) {
            if(!aop.getApproved()) {
                aop.setApproved(true);
                aop.setTime(time);
                additionOrderRepository.save(aop);
                //this.addRankingOrder(aop);

                returnResult =  AdditionOrder.builder()
                        .id(aop.getId())
                        .addition(aop.getAddition())
                        .user(aop.getUser())
                        .units(aop.getUnits())
                        .approved(aop.getApproved())
                        .time(aop.getTime())
                        .build();
            }
        }
        return returnResult;
    }

    /**
     * @param additionOrderPersistence
     * @return
     */
   /* @Override
    public RankingAdditionOrder addRankingOrder(AdditionOrderPersistence additionOrderPersistence) {
        String name = additionRepository.findNameById((long) additionOrderPersistence.getAddition());
        double price = additionRepository.findPriceById((long) additionOrderPersistence.getAddition());
        List<Integer> additionIds = rankingAdditionOrderRepository.findAllIDs();

        if(!additionIds.contains(additionOrderPersistence.getAddition())) {
            RankingAdditionOrderPersistence rankingAdditionOrderPersistence = RankingAdditionOrderPersistence.builder()
                    .numberOfTimesBought(additionOrderPersistence.getUnits())
                    .name(name)
                    .price(price)
                    .reviewedItemId(additionOrderPersistence.getAddition())
                    .build();
            RankingAdditionOrderPersistence rankingAdditionOrderPersistence2 = rankingAdditionOrderRepository.save(rankingAdditionOrderPersistence);

            RankingAdditionOrder rankingAdditionOrder = RankingAdditionOrder.builder()
                    .numberOfTimesBought(rankingAdditionOrderPersistence2.getNumberOfTimesBought())
                    .name(rankingAdditionOrderPersistence2.getName())
                    .price(rankingAdditionOrderPersistence2.getPrice())
                    .reviewedItemId(rankingAdditionOrderPersistence2.getReviewedItemId())
                    .build();
            return rankingAdditionOrder;
        }
        else {
            RankingAdditionOrderPersistence rankingAdditionOrderPersistence = rankingAdditionOrderRepository.getByAdditionId(additionOrderPersistence.getAddition());
            int units = rankingAdditionOrderPersistence.getNumberOfTimesBought();
            int totalNumber = units + additionOrderPersistence.getUnits();
            rankingAdditionOrderPersistence.setNumberOfTimesBought(totalNumber);

            RankingAdditionOrderPersistence rankingAdditionOrderPersistence2 = rankingAdditionOrderRepository.save(rankingAdditionOrderPersistence);

            RankingAdditionOrder rankingAdditionOrder = RankingAdditionOrder.builder()
                    .numberOfTimesBought(rankingAdditionOrderPersistence2.getNumberOfTimesBought())
                    .name(rankingAdditionOrderPersistence2.getName())
                    .price(rankingAdditionOrderPersistence2.getPrice())
                    .reviewedItemId(rankingAdditionOrderPersistence2.getReviewedItemId())
                    .build();
            return rankingAdditionOrder;
        }
    }*/
}
