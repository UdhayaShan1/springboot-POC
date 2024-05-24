package com.sg.gov.hdb.marvel.service;

import com.sg.gov.hdb.marvel.model.Message;
import com.sg.gov.hdb.marvel.repository.MessageRepository;
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

    public Message updateStatus(Long id, String status) {
        Message message = messageRepository.findById(id).orElse(null);
        if (message != null) {
            message.setStatus(status);
            messageRepository.save(message);
            return message;
        }
        return null;
    }

}
