package com.example.demo.business.impl.additionorder;

import com.example.demo.business.cases.additionorder.ConfirmAdditionOrderUseCase;
import com.example.demo.domain.AdditionOrder;
import com.example.demo.persistence.entity.AdditionOrderPersistence;
import com.example.demo.persistence.entity.GameOrderPersistence;
import com.example.demo.persistence.repository.AdditionOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

        for (AdditionOrderPersistence aop : list) {
            if(!aop.getApproved()) {
                aop.setApproved(true);
                aop.setTime(time);
                additionOrderRepository.save(aop);

                return AdditionOrder.builder()
                        .id(aop.getId())
                        .addition(aop.getAddition())
                        .user(aop.getUser())
                        .units(aop.getUnits())
                        .approved(aop.getApproved())
                        .time(aop.getTime())
                        .build();
            }
        }
        return AdditionOrder.builder()
                .build();
    }
}
