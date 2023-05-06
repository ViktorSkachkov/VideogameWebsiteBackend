package com.example.demo.persistence.repository;

import com.example.demo.persistence.domain.persistenceClass.AdditionOrderPersistence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdditionOrderRepository extends JpaRepository<AdditionOrderPersistence, Long> {
}
