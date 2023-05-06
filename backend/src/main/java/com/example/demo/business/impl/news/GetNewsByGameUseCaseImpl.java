package com.example.demo.business.impl.news;

import com.example.demo.business.cases.news.GetNewsByGameUseCase;
import com.example.demo.domain.News;
import com.example.demo.persistence.domain.persistenceClass.NewsPersistence;
import com.example.demo.persistence.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetNewsByGameUseCaseImpl implements GetNewsByGameUseCase {
    private final NewsRepository newsRepository;

    /**
     *
     * @param index
     * @return
     */
    @Override
    public List<News> GetNewsByGame(int index) {
        List<NewsPersistence> list = newsRepository.findAll();
        List<News> newsList = new ArrayList<>();
        News news;
        for(NewsPersistence np : list) {
            if(np.getGame_id() == index) {
                news = News.builder()
                        .id(Math.toIntExact(np.getId()))
                        .image(np.getImage())
                        .title(np.getTitle())
                        .text(np.getText())
                        .gameId(np.getGame_id())
                        .build();
                newsList.add(news);
            }
        }
        return newsList;
    }
}
