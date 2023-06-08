package com.example.demo.persistence.repository;

import com.example.demo.persistence.entity.RolePersistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<RolePersistence, Long> {
    boolean existsById(int id);

    @Query("select r from RolePersistence r")
    List<RolePersistence> findAllTest();

    @Query("select r from RolePersistence r where r.id = ?1")
    List<RolePersistence> findByid(Long id);
}
