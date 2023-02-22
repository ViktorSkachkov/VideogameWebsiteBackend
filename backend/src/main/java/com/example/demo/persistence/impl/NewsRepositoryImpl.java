package com.example.demo.persistence.impl;

import com.example.demo.domain.News;
import com.example.demo.domain.User;
import com.example.demo.persistence.repositories.NewsRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class NewsRepositoryImpl implements NewsRepository {
    List<News> news = new ArrayList<>();
    public NewsRepositoryImpl() {
        news.add(News.builder()
                        .id(1)
                        .title("Title1")
                        .text("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?")
                        .gameId(1)
                        .build());
        news.add(News.builder()
                .id(2)
                .title("Title2")
                .text("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?")
                .gameId(1)
                .build());
    }
    @Override
    public List<News> GetNews() {
        return news;
    }

    @Override
    public List<News> GetNewsForGame(int gameId) {
        List<News> returnList = new ArrayList<>();
        for(News n : news) {
            if(n.getGameId() == gameId) {
                returnList.add(n);
            }
        }
        return returnList;
    }

    @Override
    public News GetOneNews(int index) {
        News returnNews = News.builder().build();
        for(News n : news) {
            if(n.getId() == index) {
                returnNews = n;
            }
        }
        return returnNews;
    }

    @Override
    public News AddNews(News addNews) {
        news.add(addNews);
        News returnNews = News.builder().build();
        for(News n : news) {
            if(n.getId() == addNews.getId()) {
                returnNews = n;
            }
        }
        return returnNews;
    }

    @Override
    public News UpdateNews(News updateNews) {
        News returnNews = News.builder().build();
        for(News n : news) {
            if(n.getId() == updateNews.getId()) {
                returnNews = n;
            }
        }
        int index = news.indexOf(returnNews);
        news.remove(index);
        news.add(index, updateNews);
        return returnNews;
    }

    @Override
    public News DeleteNews(int index) {
        News returnNews = News.builder().build();
        for(News n : news) {
            if(n.getId() == index) {
                returnNews = n;
            }
        }
        news.remove(returnNews);
        return returnNews;
    }
}
