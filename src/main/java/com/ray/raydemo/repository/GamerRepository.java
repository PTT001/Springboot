package com.ray.raydemo.repository;

import com.ray.raydemo.model.Gamers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamerRepository extends JpaRepository<Gamers, Integer> {
}
