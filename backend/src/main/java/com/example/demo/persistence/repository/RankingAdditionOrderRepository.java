package com.example.demo.persistence.repository;

import com.example.demo.persistence.entity.RankingAdditionOrderPersistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/*public interface RankingAdditionOrderRepository extends JpaRepository<RankingAdditionOrderPersistence, Long> {
    @Query("select r.reviewedItemId from RankingAdditionOrderPersistence r")
    List<Integer> findAllIDs();

    @Query("select r from RankingAdditionOrderPersistence r where r.reviewedItemId = ?1")
    RankingAdditionOrderPersistence getByAdditionId(int additionId);
}*/
