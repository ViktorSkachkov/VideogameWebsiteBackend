package com.example.demo.persistence.repository;

import com.example.demo.persistence.entity.NewsPersistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsRepository extends JpaRepository<NewsPersistence, Long> {
    boolean existsById(int id);

    @Query("select n from NewsPersistence n")
    List<NewsPersistence> findAllTest();

    @Query("select n from NewsPersistence n where n.title = ?1")
    List<NewsPersistence> findByTitle(String title);

    @Query("select n from NewsPersistence n where n.id = ?1")
    NewsPersistence findByid(Long id);

    @Query("select n.title from NewsPersistence n")
    List<String> findAllTitles();
}
