package com.example.demo.business.impl.gameorder;

import com.example.demo.business.cases.gameorder.GetGameOrdersRankedUseCase;
import com.example.demo.domain.*;
import com.example.demo.persistence.repository.GameOrderRepository;
import com.example.demo.persistence.repository.VideogameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetGameOrdersRankedUseCaseImpl implements GetGameOrdersRankedUseCase {
    private final GameOrderRepository gameOrderRepository;
    private final VideogameRepository videogameRepository;

    @Override
    public List<RankedItem> getGameOrdersRanked(int id,  LocalDateTime endDate) {
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

        List<Integer> listOfUnits = gameOrderRepository.getUnits(startDate, endDate, true);

        List<Integer> listOfIds = gameOrderRepository.getGameIds(startDate, endDate, true);

        List<Double> listOfPrices = gameOrderRepository.getTotalPrice(startDate, endDate, true);

        List<RankedItem> rankedClassList = new ArrayList<>();

        for(int i = 0; i < listOfUnits.size(); i++) {
            String name = videogameRepository.findNameById(Long.valueOf(listOfIds.get(i)));

            rankedClassList.add(RankedItem.builder()
                    .itemId(listOfIds.get(i))
                    .units(listOfUnits.get(i))
                    .totalIncome(listOfPrices.get(i))
                    .name(name)
                    .build());
        }

        return rankedClassList;
    }
}
