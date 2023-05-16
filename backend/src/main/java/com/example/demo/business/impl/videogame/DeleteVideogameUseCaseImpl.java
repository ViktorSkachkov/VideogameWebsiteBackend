package com.example.demo.business.impl.videogame;

import com.example.demo.business.cases.videogame.DeleteVideogameUseCase;
import com.example.demo.domain.Videogame;
import com.example.demo.persistence.entity.*;
import com.example.demo.persistence.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeleteVideogameUseCaseImpl implements DeleteVideogameUseCase {
    private final VideogameRepository videogameRepository;
    private final AdditionRepository additionRepository;
    private final NewsRepository newsRepository;
    private final GameOrderRepository gameOrderRepository;
    private final AdditionOrderRepository additionOrderRepository;
    private final ReviewRepository reviewRepository;

    /**
     * @param id
     * @return
     */
    @Override
    public Videogame deleteVideogame(int id) {
        Optional<VideogamePersistence> vp = videogameRepository.findById(Long.valueOf(id));
        if (vp.isEmpty()) {

        }
        videogameRepository.deleteById(Long.valueOf(id));

        List<NewsPersistence> newsArticles = newsRepository.findAll();

        List<AdditionPersistence> additions = additionRepository.findAll();

        List<GameOrderPersistence> gameOrders = gameOrderRepository.findAll();

        List<AdditionOrderPersistence> additionOrders = additionOrderRepository.findAll();

        List<ReviewPersistence> reviews = reviewRepository.findAll();

        for(NewsPersistence np : newsArticles) {
            if(np.getGame_id() == id) {
                newsRepository.deleteById(np.getId());
            }
        }

        List<Integer> additionsIds = new ArrayList<>();

        for(AdditionPersistence ap : additions) {
            if(ap.getGame_id() == id) {
                additionsIds.add(Math.toIntExact(ap.getId()));
                additionRepository.deleteById(ap.getId());
            }
        }

        for(AdditionOrderPersistence aop : additionOrders) {
            if(additionsIds.contains(aop.getAddition())) {
                additionOrderRepository.deleteById((long) aop.getId());
            }
        }

        for(ReviewPersistence rp : reviews) {
            if((rp.getReviewed_item_id() == id && rp.getType_of_reviewed_item().equals("game")) ||
                    (additionsIds.contains(rp.getReviewed_item_id()) && rp.getType_of_reviewed_item().equals("addition"))) {
                reviewRepository.deleteById(rp.getId());
            }
        }

        for(GameOrderPersistence gop : gameOrders) {
            if(gop.getGame() == id) {
                gameOrderRepository.deleteById((long) gop.getId());
            }
        }

        return Videogame.builder()
                .id(Math.toIntExact(vp.get().getId()))
                .featured(vp.get().getFeatured())
                .description(vp.get().getDescription())
                .image(vp.get().getImage())
                .name(vp.get().getName())
                .price(vp.get().getPrice())
                .build();
    }
}
