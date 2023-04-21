package com.example.demo.persistence.repositories;

import com.example.demo.domain.persistenceClasses.AdditionOrderPersistence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdditionOrderRepository extends JpaRepository<AdditionOrderPersistence, Long> {
}
