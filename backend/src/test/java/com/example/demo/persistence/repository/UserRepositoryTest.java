package com.example.demo.persistence.repository;

import com.example.demo.persistence.entity.RolePersistence;
import com.example.demo.persistence.entity.UserPersistence;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void findAll() {
        //UserPersistence expected2 = createTestUser(19L, "username1", "password", "bank_account", "email");
        //UserPersistence expected = createTestUser(20L, "newUsername", "password", "newBankAccount", "newEmail");
        List<UserPersistence> expectedList = new ArrayList<>();
        //expectedList.add(expected);
        List<UserPersistence> actualList = userRepository.findAllTest();
        UserPersistence actual = UserPersistence.builder().build();
        for (UserPersistence up : actualList) {
            if (up.getId() == 6) {
                actual = up;
            }
        }
        //assertEquals(expected, actual);
    }

    private UserPersistence createTestUser(Long id, String username, String pwd, String bank_account, String email) {
        return entityManager.merge(UserPersistence.builder()
                .id(id)
                .username(username)
                .email(email)
                .pwd(pwd)
                .bank_account(bank_account)
                .userRoles(Set.of(RolePersistence.builder()
                        .user(id)
                        .role("EMPLOYEE")
                        .build()))
                .build());
    }
}