package com.example.demo.business.impl.videogame;

import com.example.demo.business.cases.videogame.GetVideogamesUseCase;
import com.example.demo.domain.Videogame;
import com.example.demo.persistence.entity.VideogamePersistence;
import com.example.demo.persistence.repository.VideogameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetVideogamesUseCaseImpl implements GetVideogamesUseCase {
    private final VideogameRepository videogameRepository;

    /**
     *
     * @return
     */
    @Override
    public List<Videogame> getVideogames() {
        List<VideogamePersistence> list = videogameRepository.findAll();
        List<Videogame> videogames = new ArrayList<>();
        for(VideogamePersistence vp : list) {
            Videogame videogame = Videogame.builder()
                    .id(Math.toIntExact(vp.getId()))
                    .featured(vp.getFeatured())
                    .image(vp.getImage())
                    .description(vp.getDescription())
                    .time(vp.getTime())
                    .price(vp.getPrice())
                    .name(vp.getName())
                    .build();
            videogames.add(videogame);
        }

        videogames.sort(Comparator.comparing(videogame -> videogame.getTime(), Collections.reverseOrder()));

        return videogames;
    }
}
