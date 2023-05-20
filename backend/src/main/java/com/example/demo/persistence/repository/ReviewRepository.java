package com.example.demo.persistence.repository;

import com.example.demo.persistence.entity.ReviewPersistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewPersistence, Long> {

    @Query("select r from ReviewPersistence r")
    List<ReviewPersistence> findAllTest();

    @Query("select r from ReviewPersistence r where r.id = ?1")
    ReviewPersistence findByid(Long id);

    @Query("select r from ReviewPersistence r where r.userId = ?1")
    List<ReviewPersistence> findByUser(int userId);
}
