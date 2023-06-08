package com.example.demo.business.cases.addition;

import com.example.demo.domain.Addition;
import com.example.demo.persistence.entity.ReviewPersistence;

import java.util.List;

public interface DeleteAdditionUseCase {
    Addition deleteAddition(int index);

    List<ReviewPersistence> deleteReviews(List<ReviewPersistence> reviewsList, int id);
}
