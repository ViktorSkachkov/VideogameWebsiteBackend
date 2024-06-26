package com.example.demo.business.impl.videogame;

import com.example.demo.business.cases.videogame.AddVideogameUseCase;
import com.example.demo.domain.Videogame;
import com.example.demo.persistence.entity.VideogamePersistence;
import com.example.demo.persistence.repository.VideogameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AddVideogameUseCaseImpl implements AddVideogameUseCase {
    private final VideogameRepository videogameRepository;

    /**
     * @param videogame
     * @return
     */
    @Override
    public Videogame addVideogame(Videogame videogame) {
        VideogamePersistence vp = VideogamePersistence.builder()
                .name(videogame.getName())
                .price(videogame.getPrice())
                .description(videogame.getDescription())
                .featured(videogame.getFeatured())
                .image(videogame.getImage())
                .time(LocalDateTime.now())
                .deleted(false)
                .build();
        videogameRepository.save(vp);
        return videogame;
    }
}
