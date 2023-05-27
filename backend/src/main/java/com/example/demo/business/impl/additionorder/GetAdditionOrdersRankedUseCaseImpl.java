package com.example.demo.business.impl.additionorder;

import com.example.demo.business.cases.additionorder.GetAdditionOrdersRankedUseCase;
import com.example.demo.domain.AdditionOrder;
import com.example.demo.domain.RankedClass;
import com.example.demo.domain.RankingAdditionOrder;
import com.example.demo.persistence.entity.AdditionOrderPersistence;
import com.example.demo.persistence.repository.AdditionOrderRepository;
import com.example.demo.persistence.repository.AdditionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    public List<RankedClass> getAdditionOrdersRanked(int id) {

        LocalDateTime startDate = LocalDateTime.now();

        if (id == 0) {
            startDate = LocalDateTime.of(1970, 12, 18, 14, 30, 40);
        }
        if (id == 1) {
            startDate = LocalDateTime.now().minusMonths(1);
        }
        if (id == 6) {
            startDate = LocalDateTime.now().minusMonths(6);
        }
        if (id == 12) {
            startDate = LocalDateTime.now().minusMonths(12);
        }

        LocalDateTime endDate = LocalDateTime.now().plusDays(1);

        List<Integer> listOfUnits = additionOrderRepository.getUnits(startDate, endDate, true);

        List<Integer> listOfIds = additionOrderRepository.getAdditionIds(startDate, endDate, true);

        List<Double> listOfPrices = additionOrderRepository.getTotalPrice(startDate, endDate, true);

        List<RankedClass> rankedClassList = new ArrayList<>();

        for(int i = 0; i < listOfUnits.size(); i++) {
            String name = additionRepository.findNameById(Long.valueOf(listOfIds.get(i)));

            rankedClassList.add(RankedClass.builder()
                    .additionId(listOfIds.get(i))
                    .units(listOfUnits.get(i))
                    .totalIncome(listOfPrices.get(i))
                    .name(name)
                    .build());
        }

        return rankedClassList;
    }
}
