package com.integration.service;

import com.integration.model.Message;
import com.integration.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public void deleteAllMessages() {
        messageRepository.deleteAll();
    }


}
