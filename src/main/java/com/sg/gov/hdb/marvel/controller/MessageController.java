package com.sg.gov.hdb.marvel.controller;

import com.sg.gov.hdb.marvel.model.MessageRequest;
import com.sg.gov.hdb.marvel.service.KafkaProducerService;
import com.sg.gov.hdb.marvel.service.MessageService;
import com.sg.gov.hdb.marvel.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private MessageService messageService;

    @PostMapping("/send")
    public String sendMessage(@RequestBody MessageRequest messageRequest) {
        kafkaProducerService.sendMessage(messageRequest.getMessage());
        return messageRequest.getMessage() + " message has been sent";
    }

    @GetMapping
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllMessages() {
        messageService.deleteAllMessages();
        return ResponseEntity.ok("All messages have been deleted");
    }

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
