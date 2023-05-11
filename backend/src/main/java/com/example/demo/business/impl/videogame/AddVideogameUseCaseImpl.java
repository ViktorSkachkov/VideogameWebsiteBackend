package com.example.demo.business.impl.videogame;

import com.example.demo.business.cases.videogame.AddVideogameUseCase;
import com.example.demo.domain.Videogame;
import com.example.demo.persistence.domain.persistenceClass.VideogamePersistence;
import com.example.demo.persistence.repository.VideogameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddVideogameUseCaseImpl implements AddVideogameUseCase {
    private final VideogameRepository videogameRepository;

    /**
     *
     * @param videogame
     * @return
     */
    @Override
    public Videogame addVideogame(Videogame videogame) {
        //if(videogame.getDescription().length() <= 500) {
            VideogamePersistence vp = VideogamePersistence.builder()
                    .name(videogame.getName())
                    .price(videogame.getPrice())
                    .description(videogame.getDescription())
                    .featured(videogame.getFeatured())
                    .image(videogame.getImage())
                    .build();
            videogameRepository.save(vp);
        /*}
        else {

        }*/
        return videogame;
    }
}
