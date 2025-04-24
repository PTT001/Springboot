package com.ray.raydemo.repository;

import com.ray.raydemo.model.GameRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRecordRepository extends JpaRepository<GameRecord, Long> {
}
