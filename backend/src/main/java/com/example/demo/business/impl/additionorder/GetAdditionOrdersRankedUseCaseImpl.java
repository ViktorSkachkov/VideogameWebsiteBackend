package com.example.demo.business.impl.additionorder;

import com.example.demo.business.cases.additionorder.GetAdditionOrdersRankedUseCase;
import com.example.demo.domain.RankedItem;
import com.example.demo.persistence.repository.AdditionOrderRepository;
import com.example.demo.persistence.repository.AdditionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAdditionOrdersRankedUseCaseImpl implements GetAdditionOrdersRankedUseCase {
    private final AdditionOrderRepository additionOrderRepository;
    private final AdditionRepository additionRepository;

    @Override
    public List<RankedItem> getAdditionOrdersRanked(int id,  LocalDateTime endDate) {

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

        List<RankedItem> initialRankedClassList = additionOrderRepository.getRankedAdditionItems(startDate, endDate, true);
        List<RankedItem> finalRankedClassList = new ArrayList<>();

        initialRankedClassList.forEach(element -> {
            String name = additionRepository.findNameById(Long.valueOf(element.getItemId()));
            element.setName(name);
            finalRankedClassList.add(element);
        });

        return finalRankedClassList;
    }
}
