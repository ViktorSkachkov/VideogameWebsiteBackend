package com.example.demo.persistence.repository;

import com.example.demo.persistence.entity.AdditionPersistence;
import com.example.demo.persistence.entity.GameOrderPersistence;
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
class GameOrderRepositoryTest {
    @Autowired
    private GameOrderRepository gameOrderRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void findAll() {
        GameOrderPersistence expected = createTestGameOrder(9, 25, 2, 41,
                LocalDateTime.of(2023, 04, 19, 17, 04, 32));
        GameOrderPersistence expected2 = createTestGameOrder(10, 23, 2, 41,
                LocalDateTime.of(2023, 04, 19, 17, 04, 32));
        List<GameOrderPersistence> expectedList = new ArrayList<>();
        expectedList.add(expected);
        List<GameOrderPersistence> actualList = gameOrderRepository.findAllTest();
        GameOrderPersistence actual = GameOrderPersistence.builder().build();
        for (GameOrderPersistence gop : actualList) {
            if (gop.getId() == 9) {
                actual = gop;
            }
        }
        assertEquals(expected, actual);
    }

    @Test
    void findGameOrderById() {
        GameOrderPersistence expected2 = createTestGameOrder(10, 23, 2, 41,
                LocalDateTime.of(2023, 04, 19, 17, 04, 32));
        GameOrderPersistence expected = createTestGameOrder(9, 25, 2, 41,
                LocalDateTime.of(2023, 04, 19, 17, 04, 32));
        GameOrderPersistence actual = gameOrderRepository.findByid(9L);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getGame(), actual.getGame());
        assertEquals(expected.getUnits(), actual.getUnits());
        assertEquals(expected.getUser(), actual.getUser());
    }

    private GameOrderPersistence createTestGameOrder(int id, int game, int units, int user, LocalDateTime time) {
        return entityManager.merge(GameOrderPersistence.builder()
                .id(id)
                .game(game)
                .units(units)
                .user(user)
                .time(time)
                .approved(false)
                .build());
    }
}