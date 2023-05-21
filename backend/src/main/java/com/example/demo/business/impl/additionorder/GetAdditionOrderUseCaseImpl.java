package com.example.demo.business.impl.additionorder;

import com.example.demo.business.cases.additionorder.GetAdditionOrderUseCase;
import com.example.demo.domain.AdditionOrder;
import com.example.demo.exception.IsEmptyException;
import com.example.demo.persistence.entity.AdditionOrderPersistence;
import com.example.demo.persistence.repository.AdditionOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetAdditionOrderUseCaseImpl implements GetAdditionOrderUseCase {
    private final AdditionOrderRepository additionOrderRepository;

    /**
     * @param index
     * @return
     */
    @Override
    public AdditionOrder getAdditionOrder(int index) {
        Optional<AdditionOrderPersistence> aop = additionOrderRepository.findById(Long.valueOf(index));
        if (aop.isEmpty()) {
            throw new IsEmptyException();
        }

        if( aop.isPresent()) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDateTime = aop.get().getTime().format(dateTimeFormatter);

            AdditionOrder additionOrder = AdditionOrder.builder()
                    .id(Math.toIntExact(aop.get().getId()))
                    .user(aop.get().getUser())
                    .addition(aop.get().getAddition())
                    .units(aop.get().getUnits())
                    .time(aop.get().getTime())
                    .dateFormatted(formattedDateTime)
                    .build();
            return additionOrder;

        }
        return AdditionOrder.builder().build();
    }
}
