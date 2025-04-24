package com.ray.raydemo.repository;

import com.ray.raydemo.model.GameRecord;
import com.ray.raydemo.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}

