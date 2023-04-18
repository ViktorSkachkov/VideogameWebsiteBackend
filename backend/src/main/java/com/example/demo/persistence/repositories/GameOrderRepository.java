package com.example.demo.persistence.repositories;

import com.example.demo.domain.persistenceClasses.GameOrderPersistence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameOrderRepository extends JpaRepository<GameOrderPersistence, Long> {
}
