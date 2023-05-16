package com.example.demo.business.impl.videogame;

import com.example.demo.business.cases.videogame.GetVideogamesForNewsFilterUseCase;
import com.example.demo.domain.Videogame;
import com.example.demo.persistence.entity.NewsPersistence;
import com.example.demo.persistence.entity.VideogamePersistence;
import com.example.demo.persistence.repository.NewsRepository;
import com.example.demo.persistence.repository.VideogameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetVideogamesForNewsFilterUseCaseImpl implements GetVideogamesForNewsFilterUseCase {
    private final VideogameRepository videogameRepository;
    private final NewsRepository newsRepository;

    /**
     * @return
     */
    @Override
    public List<Videogame> getVideogamesorNewsFilter() {
        List<VideogamePersistence> videogameList = videogameRepository.findAll();
        List<NewsPersistence> newsList = newsRepository.findAll();
        List<Long> videogameInts = new ArrayList<>();
        for (NewsPersistence np : newsList) {
            videogameInts.add((long) np.getGame_id());
        }

        List<Videogame> videogames = new ArrayList<>();
        for (VideogamePersistence vp : videogameList) {
            if (videogameInts.contains(vp.getId()) && !vp.getDeleted()) {
                Videogame videogame = Videogame.builder()
                        .id(Math.toIntExact(vp.getId()))
                        .featured(vp.getFeatured())
                        .image(vp.getImage())
                        .description(vp.getDescription())
                        .time(vp.getTime())
                        .price(vp.getPrice())
                        .name(vp.getName())
                        .deleted(vp.getDeleted())
                        .build();
                videogames.add(videogame);
            }
        }

        return videogames;
    }
}
