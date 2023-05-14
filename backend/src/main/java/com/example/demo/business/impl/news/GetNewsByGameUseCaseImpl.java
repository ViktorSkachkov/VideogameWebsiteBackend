package com.example.demo.business.impl.news;

import com.example.demo.business.cases.news.GetNewsByGameUseCase;
import com.example.demo.domain.News;
import com.example.demo.persistence.entity.NewsPersistence;
import com.example.demo.persistence.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    public List<News> getNewsByGame(int index) {
        List<NewsPersistence> list = newsRepository.findAll();
        List<News> newsList = new ArrayList<>();
        for(NewsPersistence np : list) {
            if(index == -1) {
                News news = News.builder()
                        .id(Math.toIntExact(np.getId()))
                        .image(np.getImage())
                        .title(np.getTitle())
                        .text(np.getText())
                        .gameId(np.getGame_id())
                        .time(np.getTime())
                        .build();
                newsList.add(news);
            }
            else {
                if(np.getGame_id() == index) {
                    News news = News.builder()
                            .id(Math.toIntExact(np.getId()))
                            .image(np.getImage())
                            .title(np.getTitle())
                            .text(np.getText())
                            .gameId(np.getGame_id())
                            .time(np.getTime())
                            .build();
                    newsList.add(news);
                }
            }
        }

        newsList.sort(Comparator.comparing(news -> news.getTime(), Collections.reverseOrder()));

        return newsList;
    }
}
