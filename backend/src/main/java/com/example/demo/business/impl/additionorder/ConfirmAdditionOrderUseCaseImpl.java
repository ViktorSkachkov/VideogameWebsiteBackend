package com.example.demo.business.impl.additionorder;

import com.example.demo.business.cases.additionorder.ConfirmAdditionOrderUseCase;
import com.example.demo.domain.AdditionOrder;
import com.example.demo.persistence.entity.AdditionOrderPersistence;
import com.example.demo.persistence.repository.AdditionOrderRepository;
import com.example.demo.persistence.repository.AdditionRepository;
//import com.example.demo.persistence.repository.RankingAdditionOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConfirmAdditionOrderUseCaseImpl implements ConfirmAdditionOrderUseCase {
    private final AdditionOrderRepository additionOrderRepository;

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
}
