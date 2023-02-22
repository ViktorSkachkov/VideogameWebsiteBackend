package com.example.demo.business.impl.news;

import com.example.demo.business.cases.news.GetNewsUseCase;
import com.example.demo.domain.News;
import com.example.demo.persistence.repositories.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetNewsUseCaseImpl implements GetNewsUseCase {
    private final NewsRepository newsRepository;

    @Override
    public List<News> GetNews() {
        return newsRepository.GetNews();
    }
}
