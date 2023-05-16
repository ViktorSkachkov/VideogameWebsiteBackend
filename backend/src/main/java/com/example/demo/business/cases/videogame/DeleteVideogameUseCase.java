package com.example.demo.business.cases.videogame;

import com.example.demo.domain.Videogame;
import com.example.demo.persistence.entity.AdditionPersistence;
import com.example.demo.persistence.entity.NewsPersistence;
import com.example.demo.persistence.entity.ReviewPersistence;

import java.util.List;

public interface DeleteVideogameUseCase {
    Videogame deleteVideogame(int index);

    List<ReviewPersistence> deleteReviews(List<ReviewPersistence> reviewsList, List<Integer> additionsIds, int id);

    List<Integer> deleteAdditions(List<AdditionPersistence> additionsList, int id);

    List<NewsPersistence> deleteNews(List<NewsPersistence> newsList, int id);
}
