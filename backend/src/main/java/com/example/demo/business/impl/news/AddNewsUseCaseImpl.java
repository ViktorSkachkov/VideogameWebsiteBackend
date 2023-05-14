package com.example.demo.business.impl.news;

import com.example.demo.business.cases.news.AddNewsUseCase;
import com.example.demo.domain.News;
import com.example.demo.persistence.entity.NewsPersistence;
import com.example.demo.persistence.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AddNewsUseCaseImpl implements AddNewsUseCase {
    private final NewsRepository newsRepository;

    /**
     *
     * @param news
     * @return
     */
    @Override
    public News addNews(News news) {
        if(news.getText().length() <= 1000) {
            NewsPersistence np = NewsPersistence.builder()
                    .game_id(news.getGameId())
                    .text(news.getText())
                    .image(news.getImage())
                    .title(news.getTitle())
                    .time(LocalDateTime.now())
                    .build();
            newsRepository.save(np);
        }
        else {

        }
        return news;
    }
}
