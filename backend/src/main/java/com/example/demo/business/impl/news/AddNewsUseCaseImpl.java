package com.example.demo.business.impl.news;

import com.example.demo.business.cases.news.AddNewsUseCase;
import com.example.demo.domain.News;
import com.example.demo.persistence.repositories.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddNewsUseCaseImpl implements AddNewsUseCase {
    private final NewsRepository newsRepository;

    @Override
    public News AddNews(News news) {
        return newsRepository.AddNews(news);
    }
}
