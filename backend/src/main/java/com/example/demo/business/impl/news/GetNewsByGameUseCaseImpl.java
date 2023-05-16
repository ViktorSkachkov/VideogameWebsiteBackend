package com.example.demo.business.impl.news;

import com.example.demo.business.cases.news.GetNewsByGameUseCase;
import com.example.demo.domain.News;
import com.example.demo.persistence.entity.NewsPersistence;
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
     * @param index
     * @return
     */
    @Override
    public List<News> getNewsByGame(int index) {
        List<NewsPersistence> list = newsRepository.findAll();
        List<News> newsList = new ArrayList<>();
        for (NewsPersistence np : list) {
            if (index == -1) {
                News news = News.builder()
                        .id(Math.toIntExact(np.getId()))
                        .image(np.getImage())
                        .title(np.getTitle())
                        .text(np.getText())
                        .gameId(np.getGame_id())
                        .time(np.getTime())
                        .build();
                newsList.add(news);
            } else {
                if (np.getGame_id() == index) {
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

        List<News> newList = reverseOrder(newsList);
        return newList;
    }

    @Override
    public List<News> reverseOrder(List<News> newsList) {
        List<News> result = new ArrayList<>();

        for (int i = newsList.size() - 1; i >= 0; i--) {
            result.add(newsList.get(i));
        }

        return result;
    }
}
