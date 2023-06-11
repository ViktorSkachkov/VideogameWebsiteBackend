package com.example.demo.business.impl.news;

import com.example.demo.business.cases.news.DeleteNewsUseCase;
import com.example.demo.domain.News;
import com.example.demo.persistence.entity.NewsPersistence;
import com.example.demo.persistence.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeleteNewsUseCaseImpl implements DeleteNewsUseCase {
    private final NewsRepository newsRepository;

    /**
     * @param id
     * @return
     */
    @Override
    public News deleteNews(int id) {
        int i;
        Optional<NewsPersistence> np = newsRepository.findById(Long.valueOf(id));
        if (np.isEmpty()) {

        }
        newsRepository.deleteById(Long.valueOf(id));
        return News.builder()
                .id(Math.toIntExact(np.get().getId()))
                .image(np.get().getImage())
                .title(np.get().getTitle())
                .text(np.get().getText())
                .gameId(np.get().getGameId())
                .build();
    }
}
