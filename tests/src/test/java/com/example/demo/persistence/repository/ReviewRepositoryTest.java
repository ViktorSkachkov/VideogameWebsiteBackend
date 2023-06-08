package com.example.demo.persistence.repository;

import com.example.demo.persistence.entity.ReviewPersistence;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ReviewRepositoryTest {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void findAll() {
        ReviewPersistence expected = createTestReview(16L, 24, "game", "Lorem Ipsum" +
                " is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the" +
                " industry's standard dummy text ever since the 1500s, when an unknown printer took a galley" +
                " of type and scrambled it to make a type specimen book.", 41, LocalDateTime.of(2023, 05, 9, 10, 42, 41));
        ReviewPersistence expected2 = createTestReview(72L, 26, "game", "Lorem Ipsum" +
                " is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the" +
                " industry's standard dummy text ever since the 1500s, when an unknown printer took a galley" +
                " of type and scrambled it to make a type specimen book.", 41, LocalDateTime.of(2023, 05, 9, 10, 42, 41));
        List<ReviewPersistence> expectedList = new ArrayList<>();
        expectedList.add(expected);
        List<ReviewPersistence> actualList = reviewRepository.findAllTest();
        ReviewPersistence actual = ReviewPersistence.builder().build();
        for (ReviewPersistence rp : actualList) {
            if (rp.getId() == 16) {
                actual = rp;
            }
        }
        assertEquals(expected, actual);
    }

    @Test
    void findNewsById() {
        ReviewPersistence expected = createTestReview(16L, 24, "game", "Lorem Ipsum" +
                " is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the" +
                " industry's standard dummy text ever since the 1500s, when an unknown printer took a galley" +
                " of type and scrambled it to make a type specimen book.", 41, LocalDateTime.of(2023, 05, 9, 10, 42, 41));
        createTestReview(72L, 26, "game", "Lorem Ipsum" +
                " is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the" +
                " industry's standard dummy text ever since the 1500s, when an unknown printer took a galley" +
                " of type and scrambled it to make a type specimen book.", 41, LocalDateTime.of(2023, 05, 9, 10, 42, 41));

        ReviewPersistence actual = reviewRepository.findByid(16L);

        assertEquals(expected, actual);
    }

    @Test
    void findReviewsByUser() {
        createTestReview(72L, 26, "game", "Lorem Ipsum" +
                " is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the" +
                " industry's standard dummy text ever since the 1500s, when an unknown printer took a galley" +
                " of type and scrambled it to make a type specimen book.", 41, LocalDateTime.of(2023, 05, 9, 10, 42, 41));
        ReviewPersistence expected = createTestReview(1L, 12, "game", "Very awesome game!" +
                        " Very awesome game! Very awesome game! Very awesome game! Very awesome game! Very awesome game!  Very awesome game!" +
                        "  Very awesome game!  Very awesome game!", 41,
                LocalDateTime.of(2023, 04, 19, 17, 04, 32));
        List<ReviewPersistence> expectedList = new ArrayList<>();
        expectedList.add(expected);
        List<ReviewPersistence> actualList = reviewRepository.findByUser(41);

        assertEquals(expectedList.get(0), actualList.get(0));
    }

    private ReviewPersistence createTestReview(Long id, int reviewed_item_id, String type_of_reviewed_item, String text, int user_id, LocalDateTime time) {
        return entityManager.merge(ReviewPersistence.builder()
                .id(id)
                .text(text)
                .reviewedItemId(reviewed_item_id)
                .time(time)
                .userId(user_id)
                .typeOfReviewedItem(type_of_reviewed_item)
                .build());
    }
}