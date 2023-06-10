package com.example.demo.persistence.repository;

import com.example.demo.persistence.entity.VideogamePersistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VideogameRepository extends JpaRepository<VideogamePersistence, Long> {
    boolean existsById(int id);

    @Query("select v from VideogamePersistence v")
    List<VideogamePersistence> findAllTest();

    @Query("select v from VideogamePersistence v where v.name = ?1")
    List<VideogamePersistence> findByName(String name);

    @Query("select v.name from VideogamePersistence v where v.id = ?1")
    String findNameById(Long id);

    @Query("select v.price from VideogamePersistence v where v.id = ?1")
    double findPriceById(Long id);

    @Query("select v from VideogamePersistence v where v.id = ?1")
    VideogamePersistence findByid(Long id);

    @Query("select v.name from VideogamePersistence v where v.deleted = ?1")
    List<String> findAllNames(boolean deleted);
}
