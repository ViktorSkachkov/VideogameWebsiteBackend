package com.example.demo.persistence.repository;

import com.example.demo.persistence.entity.AdditionOrderPersistence;
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
class AdditionOrderRepositoryTest {
    @Autowired
    private AdditionOrderRepository additionOrderRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void findAll() {
        AdditionOrderPersistence expected = createTestAdditionOrder(1, 43, 3, 41,
                LocalDateTime.of(2023, 04, 19, 17, 04, 32));
        AdditionOrderPersistence expected2 = createTestAdditionOrder(10, 41, 2, 20,
                LocalDateTime.of(2023, 04, 19, 17, 04, 32));
        List<AdditionOrderPersistence> expectedList = new ArrayList<>();
        expectedList.add(expected);
        List<AdditionOrderPersistence> actualList = additionOrderRepository.findAllTest();
        AdditionOrderPersistence actual = AdditionOrderPersistence.builder().build();
        for (AdditionOrderPersistence aop : actualList) {
            if (aop.getId() == 1) {
                actual = aop;
            }
        }
        assertEquals(expected, actual);
    }

    @Test
    void findGameOrderById() {
        AdditionOrderPersistence expected2 = createTestAdditionOrder(10, 41, 2, 20,
                LocalDateTime.of(2023, 04, 19, 17, 04, 32));
        AdditionOrderPersistence expected = createTestAdditionOrder(1, 43, 3, 41,
                LocalDateTime.of(2023, 04, 19, 17, 04, 32));
        AdditionOrderPersistence actual = additionOrderRepository.findByOrderId(1L);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getAddition(), actual.getAddition());
        assertEquals(expected.getUnits(), actual.getUnits());
        assertEquals(expected.getUser(), actual.getUser());
    }

    private AdditionOrderPersistence createTestAdditionOrder(int id, int addition, int units, int user, LocalDateTime time) {

        return entityManager.merge(AdditionOrderPersistence.builder()
                .id(id)
                .addition(addition)
                .units(units)
                .user(user)
                .time(time)
                .approved(false)
                .build());
    }
}