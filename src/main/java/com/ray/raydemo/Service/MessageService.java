package com.ray.raydemo.Service;

import com.ray.raydemo.model.GameRecord;
import com.ray.raydemo.model.Message;
import com.ray.raydemo.repository.GameRecordRepository;
import com.ray.raydemo.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageService {

    private MessageRepository messageRepository;

    // 儲存一筆 Message
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    // 查詢所有 Message
    public List<Message> findAllMessages() {
        return messageRepository.findAll();
    }
}
