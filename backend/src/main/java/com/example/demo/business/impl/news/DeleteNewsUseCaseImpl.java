package com.example.demo.business.impl.news;

import com.example.demo.business.cases.news.DeleteNewsUseCase;
import com.example.demo.domain.News;
import com.example.demo.persistence.repositories.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteNewsUseCaseImpl implements DeleteNewsUseCase {
    private final NewsRepository newsRepository;

    @Override
    public News DeleteNews(int index) {
        newsRepository.deleteById(Long.valueOf(index));
        return null;
    }
}
