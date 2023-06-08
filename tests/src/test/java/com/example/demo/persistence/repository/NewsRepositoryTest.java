package com.example.demo.persistence.repository;

import com.example.demo.persistence.entity.NewsPersistence;
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
class NewsRepositoryTest {
    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void findAll() {
        NewsPersistence expected2 = createTestNews(1L, 1, "title1", "Lorem Ipsum" +
                " is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the" +
                " industry's standard dummy text ever since the 1500s, when an unknown printer took a galley" +
                " of type and scrambled it to make a type specimen book.", "image1", LocalDateTime.of(2023, 04, 19, 17, 12, 57));
        NewsPersistence expected = createTestNews(6L, 1, "title2", "ggggggggggggggggg",
                "image", LocalDateTime.of(2023, 04, 19, 17, 12, 57));
        List<NewsPersistence> expectedList = new ArrayList<>();
        expectedList.add(expected);
        List<NewsPersistence> actualList = newsRepository.findAllTest();
        NewsPersistence actual = NewsPersistence.builder().build();
        for (NewsPersistence np : actualList) {
            if (np.getTitle() == "title2") {
                actual = np;
            }
        }
        assertEquals(expected, actual);
    }

    @Test
    void findNewsById() {
        NewsPersistence expected = createTestNews(9L, 23, "Starcraft: New Update", "Lorem Ipsum" +
                        " is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the" +
                        " industry's standard dummy text ever since the 1500s, when an unknown printer took a galley" +
                        " of type and scrambled it to make a type specimen book.", "image1",
                LocalDateTime.of(2023, 04, 19, 17, 12, 57));
        createTestNews(6L, 1, "title2", "ggggggggggggggggg",
                "image", LocalDateTime.of(2023, 04, 19, 17, 12, 57));
        //FIXME find a way to create an option list
        NewsPersistence actual = newsRepository.findByid(9L);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getGameId(), actual.getGameId());
        assertEquals(expected.getTitle(), actual.getTitle());
    }

    @Test
    void findNewsByTitle() {
        NewsPersistence expected2 = createTestNews(1L, 1, "title1", "Lorem Ipsum" +
                        " is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the" +
                        " industry's standard dummy text ever since the 1500s, when an unknown printer took a galley" +
                        " of type and scrambled it to make a type specimen book.", "image1",
                LocalDateTime.of(2023, 04, 19, 17, 12, 57));
        NewsPersistence expected = createTestNews(6L, 1, "title2", "ggggggggggggggggg",
                "image", LocalDateTime.of(2023, 04, 19, 17, 12, 57));
        List<NewsPersistence> expectedList = new ArrayList<>();
        expectedList.add(expected);
        List<NewsPersistence> actualList = newsRepository.findByTitle("title2");

        assertEquals(expectedList, actualList);
    }

    private NewsPersistence createTestNews(Long id, int game_id, String title, String text, String image, LocalDateTime time) {
        return entityManager.merge(NewsPersistence.builder()
                .id(id)
                .title(title)
                .text(text)
                .gameId(game_id)
                .image(image)
                .time(time)
                .build());
    }
}