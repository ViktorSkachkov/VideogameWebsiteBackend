package com.example.demo.persistence.repositories;

import com.example.demo.domain.persistenceClasses.UserPersistence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserPersistence, Long> {
    boolean existsById(int id);
}
