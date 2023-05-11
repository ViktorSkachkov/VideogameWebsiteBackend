package com.example.demo.persistence.repository;

import com.example.demo.persistence.domain.persistenceClass.RolePersistence;
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
class RoleRepositoryTest {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void findAll() {
        RolePersistence expected2 = createTestRole(1L,1L, "EMPLOYEE");
        RolePersistence expected = createTestRole(6L,20L, "EMPLOYEE");
        List<RolePersistence> expectedList = new ArrayList<>();
        expectedList.add(expected);
        List<RolePersistence> actualList = roleRepository.findAllTest();
        RolePersistence actual = RolePersistence.builder().build();
        for(RolePersistence rp : actualList) {
            if(rp.getId() == 6) {
                actual = rp;
            }
        }
        assertEquals(expected, actual);
    }

    @Test
    void findById() {
        RolePersistence expected2 = createTestRole(1L,1L, "EMPLOYEE");
        RolePersistence expected = createTestRole(6L,20L, "EMPLOYEE");
        List<RolePersistence> expectedList = new ArrayList<>();
        expectedList.add(expected);
        List<RolePersistence> actualList = roleRepository.findByid(6L);

        assertEquals(expectedList, actualList);
    }

    private RolePersistence createTestRole(Long id, Long user_id, String role) {
        return entityManager.merge(RolePersistence.builder()
                .id(id)
                .user(user_id)
                .role(role)
                .build());
    }
}