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

        if (vp.isPresent()) {
            vp.get().setDeleted(true);
            videogameRepository.save(vp.get());

            List<NewsPersistence> newsArticles = newsRepository.findAll();

            List<AdditionPersistence> additions = additionRepository.findAll();

            List<ReviewPersistence> reviews = reviewRepository.findAll();

            List<Integer> additionsIds = this.deleteAdditions(additions, id);

            this.deleteReviews(reviews, additionsIds, id);

            this.deleteNews(newsArticles, id);

            return Videogame.builder()
                    .id(Math.toIntExact(vp.get().getId()))
                    .featured(vp.get().getFeatured())
                    .description(vp.get().getDescription())
                    .image(vp.get().getImage())
                    .name(vp.get().getName())
                    .price(vp.get().getPrice())
                    .deleted(vp.get().getDeleted())
                    .build();
        }

        return Videogame.builder().build();
    }

    /**
     * @param reviewsList
     * @param additionsIds
     * @param id
     * @return
     */
    @Override
    public List<ReviewPersistence> deleteReviews(List<ReviewPersistence> reviewsList, List<Integer> additionsIds, int id) {
        reviewsList.forEach(rp -> {
            if((rp.getReviewedItemId() == id && rp.getTypeOfReviewedItem().equals("game")) ||
                    (additionsIds.contains(rp.getReviewedItemId()) && rp.getTypeOfReviewedItem().equals("addition"))) {
                reviewRepository.deleteById(rp.getId());
            }
        });
        return reviewsList;
    }

    /**
     * @param additionsList
     * @param id
     * @return
     */
    @Override
    public List<Integer> deleteAdditions(List<AdditionPersistence> additionsList, int id) {
        List<Integer> additionsIds = new ArrayList<>();
        additionsList.forEach(ap -> {
            if(ap.getGameId() == id) {
                additionsIds.add(Math.toIntExact(ap.getId()));
                ap.setDeleted(true);
                additionRepository.save(ap);
            }
        });
        return additionsIds;
    }


    /**
     * @param newsList
     * @param id
     * @return
     */
    @Override
    public List<NewsPersistence> deleteNews(List<NewsPersistence> newsList, int id) {
        newsList.forEach(np -> {
            if(np.getGameId() == id) {
                newsRepository.deleteById(np.getId());
            }
        });
        return newsList;
    }
}
