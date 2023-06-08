package com.example.demo.persistence.repository;

import com.example.demo.persistence.entity.VideogamePersistence;
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
class VideogameRepositoryTest {
    @Autowired
    private VideogameRepository videogameRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void findAll() {
        VideogamePersistence expected2 = createTestVideogame(1L, "Name5", 15, "Lorem" +
                        " Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been " +
                        "the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley" +
                        " of type and scrambled it to make a type specimen book.", "image", true,
                LocalDateTime.of(2023, 04, 19, 17, 04, 32));
        VideogamePersistence expected = createTestVideogame(12L, "Diablo", 12, "description",
                "image", false, LocalDateTime.of(2023, 04, 19, 17, 04, 32));
        List<VideogamePersistence> expectedList = new ArrayList<>();
        expectedList.add(expected);
        List<VideogamePersistence> actualList = videogameRepository.findAllTest();
        VideogamePersistence actual = VideogamePersistence.builder().build();
        for (VideogamePersistence vp : actualList) {
            if (vp.getName() == "Diablo") {
                actual = vp;
            }
        }
        assertEquals(expected, actual);
    }

    @Test
    void findById() {
        VideogamePersistence expected2 = createTestVideogame(1L, "Name5", 15, "Lorem" +
                        " Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been " +
                        "the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley" +
                        " of type and scrambled it to make a type specimen book.", "image", true,
                LocalDateTime.of(2023, 04, 19, 17, 04, 32));
        VideogamePersistence expected = createTestVideogame(23L, "Starcraft 2", 14, "description",
                "image", false, LocalDateTime.of(2023, 04, 19, 17, 04, 32));
        VideogamePersistence actual = videogameRepository.findByid(23L);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getPrice(), actual.getPrice());
    }

    @Test
    void findByName() {
        VideogamePersistence expected2 = createTestVideogame(1L, "Name5", 15, "Lorem" +
                        " Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been " +
                        "the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley" +
                        " of type and scrambled it to make a type specimen book.", "image", true,
                LocalDateTime.of(2023, 04, 19, 17, 04, 32));
        VideogamePersistence expected = createTestVideogame(12L, "Diablo", 12, "description",
                "image", false, LocalDateTime.of(2023, 04, 19, 17, 04, 32));
        List<VideogamePersistence> expectedList = new ArrayList<>();
        expectedList.add(expected);
        List<VideogamePersistence> actualList = videogameRepository.findByName("Diablo");
        assertEquals(expectedList, actualList);
    }

    private VideogamePersistence createTestVideogame(Long id, String name, double price, String description,
                                                     String image, Boolean featured, LocalDateTime time) {
        return entityManager.merge(VideogamePersistence.builder()
                .id(id)
                .name(name)
                .price(price)
                .description(description)
                .image(image)
                .featured(featured)
                .time(time)
                .deleted(false)
                .build());
    }
}