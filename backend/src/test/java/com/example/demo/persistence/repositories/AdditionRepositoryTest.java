package com.example.demo.persistence.repositories;

import com.example.demo.domain.persistenceClasses.AdditionPersistence;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class AdditionRepositoryTest {
    @Autowired
    private AdditionRepository additionRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void testFindByCode() {
        /*AdditionPersistence expected = createTestAddition(20, 1, "image", "Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
                " Lorem Ipsum has been the industry's standard dummy text ever since the " +
                        "1500s, when an unknown printer took a galley of type and scrambled it to " +
                        "make a type specimen book.", "NewName");
        AdditionPersistence actual = additionRepository.getAdditionPersistenceByName("NewName");
        assertEquals(expected, actual);*/
    }

    private AdditionPersistence createTestAddition(double price, int game_id, String image, String description, String name) {
        return entityManager.merge(AdditionPersistence.builder()
                        .price(price)
                        .game_id(game_id)
                        .image(image)
                        .description(description)
                        .name(name)
                .build());
    }
}