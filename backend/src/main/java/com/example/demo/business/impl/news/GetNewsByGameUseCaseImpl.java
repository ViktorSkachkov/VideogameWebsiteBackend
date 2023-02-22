package com.example.demo.business.impl.news;

import com.example.demo.business.cases.news.GetNewsByGameUseCase;
import com.example.demo.domain.News;
import com.example.demo.persistence.repositories.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetNewsByGameUseCaseImpl implements GetNewsByGameUseCase {
    private final NewsRepository newsRepository;

    @Override
    public List<News> GetNewsByGame(int index) {
        return newsRepository.GetNewsForGame(index);
    }
}
