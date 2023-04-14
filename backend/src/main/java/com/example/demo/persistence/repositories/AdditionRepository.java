package com.example.demo.persistence.repositories;

import com.example.demo.domain.persistenceClasses.AdditionPersistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AdditionRepository extends JpaRepository<AdditionPersistence, Long> {
    boolean existsById(int id);

    @Query("select a from AdditionPersistence a where a.name = ?1")
    AdditionPersistence getAdditionPersistenceByName(String name);
}
