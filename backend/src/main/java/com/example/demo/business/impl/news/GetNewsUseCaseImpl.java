package com.example.demo.business.impl.news;

import com.example.demo.business.cases.news.GetNewsUseCase;
import com.example.demo.domain.News;
import com.example.demo.persistence.domain.persistenceClass.NewsPersistence;
import com.example.demo.persistence.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetNewsUseCaseImpl implements GetNewsUseCase {
    private final NewsRepository newsRepository;

    /**
     *
     * @return
     */
    @Override
    public List<News> getNews() {
        List<NewsPersistence> list = newsRepository.findAll();
        List<News> newsList = new ArrayList<>();
        for(NewsPersistence np : list) {
            News news = News.builder()
                    .id(Math.toIntExact(np.getId()))
                    .image(np.getImage())
                    .title(np.getTitle())
                    .text(np.getText())
                    .gameId(np.getGame_id())
                    .build();
            newsList.add(news);
        }
        return newsList;
    }
}
