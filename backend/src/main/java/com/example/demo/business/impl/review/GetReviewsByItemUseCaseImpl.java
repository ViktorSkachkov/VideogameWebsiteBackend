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
     *
     * @param itemId
     * @param type
     * @return
     */
    @Override
    public List<Review> getReviewsByItem(int itemId, String type) {
        List<ReviewPersistence> list = reviewRepository.findAll();
        List<Review> newReviewList = new ArrayList<>();
        Review review;
        for(ReviewPersistence rp : list) {
            if(rp.getReviewed_item_id() == itemId &&
                    rp.getType_of_reviewed_item().equals(type)) {
                review = Review.builder()
                        .id((long) Math.toIntExact(rp.getId()))
                        .text(rp.getText())
                        .reviewed_item_id(rp.getReviewed_item_id())
                        .time(rp.getTime())
                        .user_id(rp.getUser_id())
                        .type_of_reviewed_item(rp.getType_of_reviewed_item())
                        .build();
                newReviewList.add(review);
            }
        }

        List<Review> newList = reverseOrder(newReviewList);
        return newList;
    }

    @Override
    public List<Review> reverseOrder(List<Review> reviews) {
        List<Review> result = new ArrayList<>();

        for(int i = reviews.size() - 1; i >= 0; i--) {
            result.add(reviews.get(i));
        }

        return result;
    }
}
