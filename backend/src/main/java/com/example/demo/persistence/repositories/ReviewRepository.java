package com.example.demo.persistence.repositories;

import com.example.demo.domain.persistenceClasses.ReviewPersistence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<ReviewPersistence, Long> {
}
