package com.integration.controller;

import com.integration.model.MessageRequest;
import com.integration.service.KafkaProducerService;
import com.integration.service.MessageService;
import com.integration.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
}
