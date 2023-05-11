package com.example.demo.persistence.repository;

import com.example.demo.persistence.entity.ReviewPersistence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<ReviewPersistence, Long> {
}
