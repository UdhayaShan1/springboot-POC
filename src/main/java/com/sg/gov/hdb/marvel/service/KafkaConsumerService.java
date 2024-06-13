package com.sg.gov.hdb.marvel.service;

import com.sg.gov.hdb.marvel.model.Message;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.sg.gov.hdb.marvel.repository.MessageRepository;
import com.sg.gov.hdb.marvel.repository.OrderRepository;

@Service
public class KafkaConsumerService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    //@KafkaListener(topics = "udhayacode", groupId = "batch-consumer-group")
    public void listen(String messageContent) {
        try {
            // Save the received message to the database
            Message message = new Message();
            message.setContent(messageContent);
            message.setStatus("NEW");
            messageRepository.save(message);

            // Start the batch job
            jobLauncher.run(job,
                            new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters
             ());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
