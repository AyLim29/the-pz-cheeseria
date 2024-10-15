package com.cheeseria.backend.repository;

import com.cheeseria.backend.model.Cheese;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheeseRepository extends JpaRepository<Cheese, Long>{
}
