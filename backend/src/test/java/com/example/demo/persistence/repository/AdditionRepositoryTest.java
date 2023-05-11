package com.example.demo.persistence.repository;

import com.example.demo.persistence.entity.AdditionPersistence;
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
class AdditionRepositoryTest {
    @Autowired
    private AdditionRepository additionRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void findAll() {
        AdditionPersistence expected2 = createTestAddition(5L,20, 1, "image", "Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
                " Lorem Ipsum has been the industry's standard dummy text ever since the " +
                "1500s, when an unknown printer took a galley of type and scrambled it to " +
                "make a type specimen book.", "NewName");
        AdditionPersistence expected = createTestAddition(5L,20, 1, "image", "Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
                " Lorem Ipsum has been the industry's standard dummy text ever since the " +
                "1500s, when an unknown printer took a galley of type and scrambled it to " +
                "make a type specimen book.", "name5");
        List<AdditionPersistence> expectedList = new ArrayList<>();
        expectedList.add(expected);
        List<AdditionPersistence> actualList = additionRepository.findAllTest();
        AdditionPersistence actual = AdditionPersistence.builder().build();
        for(AdditionPersistence ap : actualList) {
            if(ap.getName() == "name5") {
                actual = ap;
            }
        }
        assertEquals(expected, actual);
    }

    @Test
    void findAdditionByName() {
        AdditionPersistence expected2 = createTestAddition(5L,20, 1, "image", "Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
                " Lorem Ipsum has been the industry's standard dummy text ever since the " +
                "1500s, when an unknown printer took a galley of type and scrambled it to " +
                "make a type specimen book.", "NewName");
        AdditionPersistence expected = createTestAddition(5L,10, 2, "image", "Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
                " Lorem Ipsum has been the industry's standard dummy text ever since the " +
                "1500s, when an unknown printer took a galley of type and scrambled it to " +
                "make a type specimen book.", "name5");
        List<AdditionPersistence> expectedList = new ArrayList<>();
        expectedList.add(expected);
        List<AdditionPersistence> actualList = additionRepository.findByName("name5");
        assertEquals(expectedList, actualList);
    }

    @Test
    void findAdditionById() {
        AdditionPersistence expected2 = createTestAddition(4L,20, 1, "image", "Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
                " Lorem Ipsum has been the industry's standard dummy text ever since the " +
                "1500s, when an unknown printer took a galley of type and scrambled it to " +
                "make a type specimen book.", "NewName");
        AdditionPersistence expected = createTestAddition(41L,6, 23, "image", "Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
                " Lorem Ipsum has been the industry's standard dummy text ever since the " +
                "1500s, when an unknown printer took a galley of type and scrambled it to " +
                "make a type specimen book.", "name5");
        AdditionPersistence actual = additionRepository.findByid(41L);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getGame_id(), actual.getGame_id());
        assertEquals(expected.getPrice(), actual.getPrice());
    }

    private AdditionPersistence createTestAddition(Long id, double price, int game_id, String image, String description, String name) {
        return entityManager.merge(AdditionPersistence.builder()
                        .id(id)
                        .price(price)
                        .game_id(game_id)
                        .image(image)
                        .description(description)
                        .name(name)
                .build());
    }
}