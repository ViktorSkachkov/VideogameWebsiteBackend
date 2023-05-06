package com.example.demo.persistence.repository;

import com.example.demo.persistence.domain.persistenceClass.GameOrderPersistence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameOrderRepository extends JpaRepository<GameOrderPersistence, Long> {
}
