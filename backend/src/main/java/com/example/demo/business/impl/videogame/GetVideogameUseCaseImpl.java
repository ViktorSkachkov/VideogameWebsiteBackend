package com.example.demo.business.impl.videogame;

import com.example.demo.business.cases.videogame.GetVideogameUseCase;
import com.example.demo.domain.Videogame;
import com.example.demo.persistence.domain.persistenceClass.VideogamePersistence;
import com.example.demo.persistence.repository.VideogameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetVideogameUseCaseImpl implements GetVideogameUseCase {
    private final VideogameRepository videogameRepository;

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Videogame getVideogame(int id) {
        Optional<VideogamePersistence> vp = videogameRepository.findById(Long.valueOf(id));
        if(vp.isEmpty()) {

        }
        Videogame videogame = Videogame.builder()
                .id(Math.toIntExact(vp.get().getId()))
                .featured(vp.get().getFeatured())
                .description(vp.get().getDescription())
                .image(vp.get().getImage())
                .name(vp.get().getName())
                .price(vp.get().getPrice())
                .build();
        return videogame;
    }
}
