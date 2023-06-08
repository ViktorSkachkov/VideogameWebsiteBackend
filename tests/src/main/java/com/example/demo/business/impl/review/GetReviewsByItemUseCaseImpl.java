package com.example.demo.business.impl.review;

import com.example.demo.business.cases.review.GetReviewsByItemUseCase;
import com.example.demo.domain.Review;
import com.example.demo.persistence.entity.ReviewPersistence;
import com.example.demo.persistence.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetReviewsByItemUseCaseImpl implements GetReviewsByItemUseCase {
    private final ReviewRepository reviewRepository;

    /**
     * @param itemId
     * @param type
     * @return
     */
    @Override
    public List<Review> getReviewsByItem(int itemId, String type) {
        List<ReviewPersistence> list = reviewRepository.findAll();
        List<Review> newReviewList = new ArrayList<>();

        list.forEach(rp -> {
            Review review;
            if (rp.getReviewedItemId() == itemId &&
                    rp.getTypeOfReviewedItem().equals(type)) {
                review = Review.builder()
                        .id((long) Math.toIntExact(rp.getId()))
                        .text(rp.getText())
                        .reviewedItemId(rp.getReviewedItemId())
                        .time(rp.getTime())
                        .userId(rp.getUserId())
                        .typeOfReviewedItem(rp.getTypeOfReviewedItem())
                        .build();
                newReviewList.add(review);
            }
        });

        List<Review> newList = reverseOrder(newReviewList);
        return newList;
    }

    @Override
    public List<Review> reverseOrder(List<Review> reviews) {
        List<Review> result = new ArrayList<>();

        for (int i = reviews.size() - 1; i >= 0; i--) {
            result.add(reviews.get(i));
        }

        return result;
    }
}
