package com.example.demo.persistence.repository;

import com.example.demo.persistence.entity.GameOrderPersistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface GameOrderRepository extends JpaRepository<GameOrderPersistence, Long> {

    @Query("select gop from GameOrderPersistence gop")
    List<GameOrderPersistence> findAllTest();

    @Query("select gop from GameOrderPersistence gop where gop.id = ?1")
    GameOrderPersistence findByid(Long id);

    @Query("select gop from GameOrderPersistence gop where gop.user = ?1")
    List<GameOrderPersistence> findByUserId(Long id);

    @Query("select gop from GameOrderPersistence gop where gop.user = ?1 and gop.approved = ?2")
    List<GameOrderPersistence> findCartItemsByUserId(Long id, Boolean approved);

    @Query("select gop.game, sum(gop.units) from GameOrderPersistence gop where gop.approved = ?3 and gop.time between ?1 and ?2 group by gop.game order by sum(gop.totalPrice) desc")
    List<Integer> getGameIds(LocalDateTime startDate, LocalDateTime endDate, Boolean approved);

    @Query("select sum(gop.units) from GameOrderPersistence gop where gop.approved = ?3 and gop.time between ?1 and ?2 group by gop.game order by sum(gop.totalPrice) desc")
    List<Integer> getUnits(LocalDateTime startDate, LocalDateTime endDate, Boolean approved);

    @Query("select sum(gop.totalPrice) from GameOrderPersistence gop where gop.approved = ?3 and gop.time between ?1 and ?2 group by gop.game order by sum(gop.totalPrice) desc")
    List<Double> getTotalPrice(LocalDateTime startDate, LocalDateTime endDate, Boolean approved);
}
