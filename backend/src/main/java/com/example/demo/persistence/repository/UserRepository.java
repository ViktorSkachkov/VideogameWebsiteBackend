package com.example.demo.persistence.repository;

import com.example.demo.persistence.entity.UserPersistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserPersistence, Long> {
    boolean existsById(int id);

    UserPersistence findByUsername(String username);

    @Query("select u from UserPersistence u")
    List<UserPersistence> findAllTest();
}
