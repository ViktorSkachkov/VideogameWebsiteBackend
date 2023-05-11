package com.example.demo.business.impl.news;

import com.example.demo.business.cases.news.UpdateNewsUseCase;
import com.example.demo.domain.News;
import com.example.demo.persistence.entity.NewsPersistence;
import com.example.demo.persistence.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateNewsUseCaseImpl implements UpdateNewsUseCase {
    private final NewsRepository newsRepository;

    /**
     *
     * @param news
     * @return
     */
    @Override
    public News updateNews(News news) {
        Optional<NewsPersistence> np = newsRepository.findById(Long.valueOf(news.getId()));
        if(np.isEmpty()) {

        }
        np.get().setGame_id(news.getGameId());
        np.get().setText(news.getText());
        np.get().setImage(news.getImage());
        np.get().setTitle(news.getTitle());
        newsRepository.save(np.get());
        return news;
    }
}
