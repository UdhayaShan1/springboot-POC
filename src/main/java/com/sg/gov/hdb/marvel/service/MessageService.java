package com.sg.gov.hdb.marvel.service;

import com.sg.gov.hdb.marvel.model.Message;
import com.sg.gov.hdb.marvel.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for managing messages.
 */
@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    /**
     * Retrieves all messages from the repository.
     *
     * @return a list of all messages
     */
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    /**
     * Deletes all messages from the repository.
     */
    public void deleteAllMessages() {
        messageRepository.deleteAll();
    }

    /**
     * Updates the status of a message.
     *
     * @param id the ID of the message to update
     * @param status the new status of the message
     * @return the updated message, or null if the message was not found
     */
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
