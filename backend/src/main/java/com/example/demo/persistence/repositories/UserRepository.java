package com.example.demo.persistence.repositories;

import com.example.demo.domain.persistenceClasses.RolePersistence;
import com.example.demo.domain.persistenceClasses.UserPersistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserPersistence, Long> {
    boolean existsById(int id);
    UserPersistence findByUsername(String username);

    @Query("select u from UserPersistence u")
    List<UserPersistence> findAllTest();
}
