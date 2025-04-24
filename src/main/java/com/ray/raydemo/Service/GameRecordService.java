package com.ray.raydemo.Service;

import com.ray.raydemo.model.GameRecord;
import com.ray.raydemo.repository.GameRecordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GameRecordService {

    private GameRecordRepository gameRecordRepository;

    // 儲存一筆 GameRecord
    public GameRecord saveGameRecord(GameRecord gameRecord) {
        return gameRecordRepository.save(gameRecord);
    }

    // 查詢所有 GameRecord
    public List<GameRecord> findAllGameRecords() {
        return gameRecordRepository.findAll();
    }
}
