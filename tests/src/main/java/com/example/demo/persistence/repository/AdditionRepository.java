package com.example.demo.persistence.repository;

import com.example.demo.persistence.entity.AdditionPersistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdditionRepository extends JpaRepository<AdditionPersistence, Long> {
    boolean existsById(int id);

    @Query("select a from AdditionPersistence a")
    List<AdditionPersistence> findAllTest();

    @Query("select a from AdditionPersistence a where a.name = ?1")
    List<AdditionPersistence> findByName(String name);

    @Query("select a.name from AdditionPersistence a where a.id = ?1")
    String findNameById(Long id);

    @Query("select a.price from AdditionPersistence a where a.id = ?1")
    double findPriceById(Long id);

    @Query("select a from AdditionPersistence a where a.id = ?1")
    AdditionPersistence findByAdditionId(Long id);

    @Query("select a.name from AdditionPersistence a where a.deleted = ?1")
    List<String> findAllNames(boolean deleted);
}
