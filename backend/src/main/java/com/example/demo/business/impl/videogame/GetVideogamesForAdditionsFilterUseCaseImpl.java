package com.example.demo.business.impl.videogame;

import com.example.demo.business.cases.videogame.GetVideogamesForAdditionsFilterUseCase;
import com.example.demo.domain.Videogame;
import com.example.demo.persistence.entity.AdditionPersistence;
import com.example.demo.persistence.entity.VideogamePersistence;
import com.example.demo.persistence.repository.AdditionRepository;
import com.example.demo.persistence.repository.VideogameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetVideogamesForAdditionsFilterUseCaseImpl implements GetVideogamesForAdditionsFilterUseCase {
    private final VideogameRepository videogameRepository;
    private final AdditionRepository additionRepository;

    /**
     * @return
     */
    @Override
    public List<Videogame> getVideogamesorAdditionsFilter() {
        List<VideogamePersistence> videogameList = videogameRepository.findAll();
        List<AdditionPersistence> additionList = additionRepository.findAll();
        List<Long> videogameInts = new ArrayList<>();
        for (AdditionPersistence ap : additionList) {
            if(!ap.getDeleted()) {
                videogameInts.add((long) ap.getGameId());
            }
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
        //return videogameInts;
    }
}
