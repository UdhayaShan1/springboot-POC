package com.sg.gov.hdb.marvel.service;

import com.sg.gov.hdb.marvel.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.sg.gov.hdb.marvel.repository.MessageRepository;

@Service
public class KafkaProducerService {

    private static final String TOPIC = "udhayacode";

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        kafkaTemplate.send(TOPIC, message);

        Message message1 = new Message();
        message1.setContent(message);
        //messageRepository.save(message1);
    }
}
