package com.sg.gov.hdb.marvel.controller;

import com.sg.gov.hdb.marvel.model.MessageRequest;
import com.sg.gov.hdb.marvel.service.KafkaProducerService;
import com.sg.gov.hdb.marvel.service.MessageService;
import com.sg.gov.hdb.marvel.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing messages.
 */
@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private MessageService messageService;

    /**
     * Sends a message to Kafka.
     *
     * @param messageRequest the request containing the message to be sent
     * @return a confirmation message
     */
    @PostMapping("/send")
    public String sendMessage(@RequestBody MessageRequest messageRequest) {
        kafkaProducerService.sendMessage(messageRequest.getMessage());
        return messageRequest.getMessage() + " message has been sent";
    }

    /**
     * Retrieves all messages.
     *
     * @return a list of all messages
     */
    @GetMapping
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    /**
     * Deletes all messages.
     *
     * @return a ResponseEntity with a confirmation message
     */
    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllMessages() {
        messageService.deleteAllMessages();
        return ResponseEntity.ok("All messages have been deleted");
    }

    /**
     * Updates the status of a message.
     *
     * @param id the ID of the message to update
     * @param status the new status of the message
     * @return a ResponseEntity containing the updated message and HTTP status code
     */
    @PutMapping("/{id}")
    public ResponseEntity<Message> updateMessageStatus(@PathVariable Long id, @RequestBody MessageRequest status) {
        Message updatedMessage = messageService.updateStatus(id, status.getMessage());
        if (updatedMessage != null) {
            return ResponseEntity.ok(updatedMessage);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
