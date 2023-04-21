package com.example.demo.business.impl.reviews;

import com.example.demo.business.cases.reviews.GetReviewsByItemUseCase;
import com.example.demo.domain.Review;
import com.example.demo.domain.ReviewRequest;
import com.example.demo.domain.persistenceClasses.ReviewPersistence;
import com.example.demo.persistence.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class GetReviewsByItemUseCaseImpl implements GetReviewsByItemUseCase {
    private final ReviewRepository reviewRepository;

    /*@Override
    public List<Review> GetReviewsByItem(ReviewRequest reviewRequest) {
        List<ReviewPersistence> list = reviewRepository.findAll();
        List<Review> newReviewList = new ArrayList<>();
        Review review;
        for(ReviewPersistence rp : list) {
            if(rp.getReviewed_item_id() == reviewRequest.getReviewed_item_id() &&
                    rp.getType_of_reviewed_item().equals(reviewRequest.getType_of_reviewed_item())) {
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
        return newReviewList;
    }*/

    @Override
    public List<Review> GetReviewsByItem(int itemId, String type) {
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
        return newReviewList;
    }
}
