package com.example.demo.business.impl.videogame;

import com.example.demo.business.cases.videogame.UpdateVideogameUseCase;
import com.example.demo.domain.Videogame;
import com.example.demo.persistence.entity.VideogamePersistence;
import com.example.demo.persistence.repository.VideogameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateVideogameUseCaseImpl implements UpdateVideogameUseCase {
    private final VideogameRepository videogameRepository;

    /**
     *
     * @param videogame
     * @return
     */
    @Override
    public Videogame updateVideogame(Videogame videogame) {
        Optional<VideogamePersistence> vp = videogameRepository.findById(Long.valueOf(videogame.getId()));
        if(vp.isEmpty()) {

        }
        vp.get().setDescription(videogame.getDescription());
        vp.get().setFeatured(videogame.getFeatured());
        vp.get().setImage(videogame.getImage());
        vp.get().setName(videogame.getName());
        vp.get().setPrice(videogame.getPrice());
        videogameRepository.save(vp.get());
        return videogame;
    }
}
