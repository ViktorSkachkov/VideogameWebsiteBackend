package com.example.demo.business.impl.news;

import com.example.demo.business.cases.news.GetNewsUseCase;
import com.example.demo.domain.News;
import com.example.demo.persistence.entity.NewsPersistence;
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
     * @return
     */
    @Override
    public List<News> getNews() {
        int i;
        List<NewsPersistence> list = newsRepository.findAll();
        List<News> newsList = new ArrayList<>();

        list.forEach(np -> {
            News news = News.builder()
                    .id(Math.toIntExact(np.getId()))
                    .image(np.getImage())
                    .title(np.getTitle())
                    .text(np.getText())
                    .gameId(np.getGameId())
                    .time(np.getTime())
                    .build();
            newsList.add(news);
        });

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
