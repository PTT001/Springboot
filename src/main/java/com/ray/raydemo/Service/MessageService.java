package com.ray.raydemo.Service;

import com.ray.raydemo.model.GameRecord;
import com.ray.raydemo.model.Message;
import com.ray.raydemo.model.User;
import com.ray.raydemo.repository.GameRecordRepository;
import com.ray.raydemo.repository.MessageRepository;
import com.ray.raydemo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageService {

    private MessageRepository messageRepository;
    private UserRepository userRepository;

    // 儲存一筆 Message
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    // 查詢所有 Message
    public List<Message> findAllMessages() {
        List<Message> messages = messageRepository.findAll();

        // 遍歷每則訊息，根據 username 查找 avatar
        for (Message message : messages) {
            userRepository.findByUsername(message.getUsername())
                    .ifPresent(user -> message.setAvatarUrl(user.getAvatarUrl()));
        }

        return messages;
    }
}
