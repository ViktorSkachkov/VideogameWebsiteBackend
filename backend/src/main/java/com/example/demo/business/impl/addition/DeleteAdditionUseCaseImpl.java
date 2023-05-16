package com.example.demo.business.impl.addition;

import com.example.demo.business.cases.addition.DeleteAdditionUseCase;
import com.example.demo.domain.Addition;
import com.example.demo.persistence.entity.AdditionOrderPersistence;
import com.example.demo.persistence.entity.AdditionPersistence;
import com.example.demo.persistence.entity.ReviewPersistence;
import com.example.demo.persistence.repository.AdditionOrderRepository;
import com.example.demo.persistence.repository.AdditionRepository;
import com.example.demo.persistence.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeleteAdditionUseCaseImpl implements DeleteAdditionUseCase {
    private final AdditionRepository additionRepository;
    private final AdditionOrderRepository additionOrderRepository;
    private final ReviewRepository reviewRepository;

    /**
     * @param id
     * @return
     */
    @Override
    public Addition deleteAddition(int id) {
        Optional<AdditionPersistence> ap = additionRepository.findById(Long.valueOf(id));
        if (ap.isEmpty()) {

        }
        additionRepository.deleteById(Long.valueOf(id));

        List<AdditionOrderPersistence> ordersList = additionOrderRepository.findAll();

        for(AdditionOrderPersistence aop : ordersList) {
            if(aop.getAddition() == id) {
                additionOrderRepository.deleteById((long) aop.getId());
            }
        }

        List<ReviewPersistence> reviewsList = reviewRepository.findAll();

        for(ReviewPersistence rp : reviewsList) {
            if(rp.getReviewed_item_id() == id && rp.getType_of_reviewed_item().equals("addition")) {
                reviewRepository.deleteById((long) rp.getId());
            }
        }

        return Addition.builder()
                .id(Math.toIntExact(ap.get().getId()))
                .gameId(ap.get().getGame_id())
                .image(ap.get().getImage())
                .name(ap.get().getName())
                .description(ap.get().getDescription())
                .price(ap.get().getPrice())
                .build();
    }
}
