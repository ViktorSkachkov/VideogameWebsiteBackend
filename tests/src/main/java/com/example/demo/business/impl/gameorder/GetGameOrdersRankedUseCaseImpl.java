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

        List<RankedItem> initialRankedClassList = gameOrderRepository.getRankedGameItems(startDate, endDate, true);
        List<RankedItem> finalRankedClassList = new ArrayList<>();

        initialRankedClassList.forEach(element -> {
            String name = videogameRepository.findNameById(Long.valueOf(element.getItemId()));
            element.setName(name);
            finalRankedClassList.add(element);
        });

        return finalRankedClassList;
    }
}
