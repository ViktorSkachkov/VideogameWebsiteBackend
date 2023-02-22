package com.example.demo.business.impl.news;

import com.example.demo.business.cases.news.GetOneNewsUseCase;
import com.example.demo.domain.News;
import com.example.demo.persistence.repositories.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetOneNewsUseCaseImpl implements GetOneNewsUseCase {
    private final NewsRepository newsRepository;
    @Override
    public News GetOneNews(int index) {
        return newsRepository.GetOneNews(index);
    }
}
