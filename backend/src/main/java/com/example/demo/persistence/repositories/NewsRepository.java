package com.example.demo.persistence.repositories;

import com.example.demo.domain.News;

import java.util.List;

public interface NewsRepository {
    List<News> GetNews();
    List<News> GetNewsForGame(int gameId);
    News GetOneNews(int index);
    News AddNews(News news);
    News UpdateNews(News news);
    News DeleteNews(int index);
}
