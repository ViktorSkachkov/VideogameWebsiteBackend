package com.example.demo.business.impl.news;

import com.example.demo.business.cases.news.UpdateNewsUseCase;
import com.example.demo.domain.News;
import com.example.demo.persistence.repositories.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateNewsUseCaseImpl implements UpdateNewsUseCase {
    private final NewsRepository newsRepository;
    @Override
    public News UpdateNews(News news) {
        return newsRepository.UpdateNews(news);
    }
}
