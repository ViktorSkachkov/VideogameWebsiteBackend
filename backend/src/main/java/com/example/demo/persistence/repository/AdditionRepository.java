package com.example.demo.persistence.repository;

import com.example.demo.persistence.domain.persistenceClass.AdditionPersistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdditionRepository extends JpaRepository<AdditionPersistence, Long> {
    boolean existsById(int id);

    @Query("select a from AdditionPersistence a")
    List<AdditionPersistence> findAllTest();

    @Query("select a from AdditionPersistence a where a.name = ?1")
    List<AdditionPersistence> findByName(String name);

    @Query("select a from AdditionPersistence a where a.id = ?1")
    AdditionPersistence findByid(Long id);
}
