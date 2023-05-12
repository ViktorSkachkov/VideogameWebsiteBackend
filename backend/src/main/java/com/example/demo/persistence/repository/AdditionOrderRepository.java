package com.example.demo.persistence.repository;

import com.example.demo.persistence.entity.AdditionOrderPersistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdditionOrderRepository extends JpaRepository<AdditionOrderPersistence, Long> {

    @Query("select aop from AdditionOrderPersistence aop")
    List<AdditionOrderPersistence> findAllTest();

    @Query("select aop from AdditionOrderPersistence aop where aop.id = ?1")
    AdditionOrderPersistence findByid(Long id);
}
