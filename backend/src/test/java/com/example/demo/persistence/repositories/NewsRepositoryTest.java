package com.example.demo.persistence.repositories;

import com.example.demo.domain.persistenceClasses.AdditionPersistence;
import com.example.demo.domain.persistenceClasses.NewsPersistence;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
        NewsPersistence expected2 = createTestNews(1L,1, "title1", "Lorem Ipsum" +
                " is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the" +
                " industry's standard dummy text ever since the 1500s, when an unknown printer took a galley" +
                " of type and scrambled it to make a type specimen book.", "image1");
        NewsPersistence expected = createTestNews(6L,1, "title2", "ggggggggggggggggg",
                "image");
        List<NewsPersistence> expectedList = new ArrayList<>();
        expectedList.add(expected);
        List<NewsPersistence> actualList = newsRepository.findAllTest();
        NewsPersistence actual = NewsPersistence.builder().build();
        for(NewsPersistence np : actualList) {
            if(np.getTitle() == "title2") {
                actual = np;
            }
        }
        assertEquals(expected, actual);
    }

    @Test
    void findNewsById() {
        NewsPersistence expected2 = createTestNews(1L,1, "title1", "Lorem Ipsum" +
                " is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the" +
                " industry's standard dummy text ever since the 1500s, when an unknown printer took a galley" +
                " of type and scrambled it to make a type specimen book.", "image1");
        NewsPersistence expected = createTestNews(6L,1, "title2", "ggggggggggggggggg",
                "image");
        List<NewsPersistence> expectedList = new ArrayList<>();
        expectedList.add(expected);
        List<NewsPersistence> actualList = newsRepository.findByid(6L);

        assertEquals(expectedList, actualList);
    }

    @Test
    void findNewsByTitle() {
        NewsPersistence expected2 = createTestNews(1L,1, "title1", "Lorem Ipsum" +
                " is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the" +
                " industry's standard dummy text ever since the 1500s, when an unknown printer took a galley" +
                " of type and scrambled it to make a type specimen book.", "image1");
        NewsPersistence expected = createTestNews(6L,1, "title2", "ggggggggggggggggg",
                "image");
        List<NewsPersistence> expectedList = new ArrayList<>();
        expectedList.add(expected);
        List<NewsPersistence> actualList = newsRepository.findByTitle("title2");

        assertEquals(expectedList, actualList);
    }

    private NewsPersistence createTestNews(Long id, int game_id, String title, String text, String image) {
        return entityManager.merge(NewsPersistence.builder()
                .id(id)
                .title(title)
                .text(text)
                .game_id(game_id)
                .image(image)
                .build());
    }
}