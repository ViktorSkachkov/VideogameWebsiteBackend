package com.example.demo.business.impl.addition;

import com.example.demo.business.cases.addition.DeleteAdditionUseCase;
import com.example.demo.domain.Addition;
import com.example.demo.domain.Review;
import com.example.demo.exception.IsEmptyException;
import com.example.demo.persistence.entity.AdditionOrderPersistence;
import com.example.demo.persistence.entity.AdditionPersistence;
import com.example.demo.persistence.entity.ReviewPersistence;
import com.example.demo.persistence.repository.AdditionOrderRepository;
import com.example.demo.persistence.repository.AdditionRepository;
import com.example.demo.persistence.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeleteAdditionUseCaseImpl implements DeleteAdditionUseCase {
    private final AdditionRepository additionRepository;
    private final ReviewRepository reviewRepository;

    /**
     * @param id
     * @return
     */
    @Override
    public Addition deleteAddition(int id) {
        Optional<AdditionPersistence> ap = additionRepository.findById(Long.valueOf(id));
        if (ap.isEmpty()) {
            throw new IsEmptyException();
        }

        if (ap.isPresent()) {
            ap.get().setDeleted(true);
            additionRepository.save(ap.get());

            List<ReviewPersistence> reviewsList = reviewRepository.findAll();

            deleteReviews(reviewsList, id);

        }

        return Addition.builder()
                .id(Math.toIntExact(ap.get().getId()))
                .gameId(ap.get().getGameId())
                .image(ap.get().getImage())
                .name(ap.get().getName())
                .description(ap.get().getDescription())
                .price(ap.get().getPrice())
                .deleted(ap.get().getDeleted())
                .build();
    }

    @Override
    public List<ReviewPersistence> deleteReviews(List<ReviewPersistence> reviewsList, int id) {
        reviewsList.forEach(rp ->
        {
            if(rp.getReviewedItemId() == id && rp.getTypeOfReviewedItem().equals("addition")) {
                reviewRepository.deleteById(rp.getId());
            }
        });
        return reviewsList;
    }
}
