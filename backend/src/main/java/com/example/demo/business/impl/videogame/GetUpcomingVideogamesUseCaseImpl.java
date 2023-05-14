package com.example.demo.business.impl.videogame;

import com.example.demo.business.cases.videogame.GetUpcomingVideogamesUseCase;
import com.example.demo.domain.Videogame;
import com.example.demo.persistence.entity.VideogamePersistence;
import com.example.demo.persistence.repository.VideogameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetUpcomingVideogamesUseCaseImpl implements GetUpcomingVideogamesUseCase {
    private final VideogameRepository videogameRepository;

    /**
     *
     * @return
     */
    @Override
    public List<Videogame> getUpcomingVideogames() {
        List<VideogamePersistence> list = videogameRepository.findAll();
        List<Videogame> videogames = new ArrayList<>();
        Videogame videogame;
        for(VideogamePersistence vp : list) {
            videogame = Videogame.builder()
                    .id(Math.toIntExact(vp.getId()))
                    .featured(vp.getFeatured())
                    .image(vp.getImage())
                    .description(vp.getDescription())
                    .price(vp.getPrice())
                    .name(vp.getName())
                    .time(vp.getTime())
                    .build();
            videogames.add(videogame);
        }
        return videogames;
    }
}
