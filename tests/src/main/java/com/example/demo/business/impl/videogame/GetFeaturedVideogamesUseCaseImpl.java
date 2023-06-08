package com.example.demo.business.impl.videogame;

import com.example.demo.business.cases.videogame.GetFeaturedVideogamesUseCase;
import com.example.demo.domain.Videogame;
import com.example.demo.persistence.entity.VideogamePersistence;
import com.example.demo.persistence.repository.VideogameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetFeaturedVideogamesUseCaseImpl implements GetFeaturedVideogamesUseCase {
    private final VideogameRepository videogameRepository;

    /**
     * @return
     */
    @Override
    public List<Videogame> getFeaturedVideogames() {
        List<VideogamePersistence> list = videogameRepository.findAll();
        List<Videogame> videogames = new ArrayList<>();

        list.forEach(vp -> {
            Videogame videogame;
            if (vp.getFeatured() && !vp.getDeleted()) {
                videogame = Videogame.builder()
                        .id(Math.toIntExact(vp.getId()))
                        .featured(vp.getFeatured())
                        .image(vp.getImage())
                        .description(vp.getDescription())
                        .price(vp.getPrice())
                        .name(vp.getName())
                        .time(vp.getTime())
                        .deleted(vp.getDeleted())
                        .build();
                videogames.add(videogame);
            }
        });

        List<Videogame> newList = reverseOrder(videogames);
        return newList;
    }

    @Override
    public List<Videogame> reverseOrder(List<Videogame> videogames) {
        List<Videogame> result = new ArrayList<>();

        for (int i = videogames.size() - 1; i >= 0; i--) {
            result.add(videogames.get(i));
        }

        return result;
    }
}
