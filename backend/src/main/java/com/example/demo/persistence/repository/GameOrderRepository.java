package com.example.demo.persistence.repository;

import com.example.demo.persistence.entity.GameOrderPersistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameOrderRepository extends JpaRepository<GameOrderPersistence, Long> {

    @Query("select gop from GameOrderPersistence gop")
    List<GameOrderPersistence> findAllTest();

    @Query("select gop from GameOrderPersistence gop where gop.id = ?1")
    GameOrderPersistence findByid(Long id);

    @Query("select gop from GameOrderPersistence gop where gop.user = ?1")
     List<GameOrderPersistence> findByUserId(Long id);
}
