package com.sg.gov.hdb.marvel.config;

import com.sg.gov.hdb.marvel.model.Message;
import com.sg.gov.hdb.marvel.model.CustomerOrder;
import com.sg.gov.hdb.marvel.repository.MessageRepository;
import com.sg.gov.hdb.marvel.repository.OrderRepository;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;


@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public Job job() {
        return new JobBuilder("job", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() {
        return new StepBuilder("step1", jobRepository)
                .<Message, CustomerOrder>chunk(10, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public JpaPagingItemReader<Message> reader() {
        return new JpaPagingItemReaderBuilder<Message>()
                .name("messageReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("SELECT m FROM Message m")
                .pageSize(10)
                .build();
    }

    @Bean
    public ItemProcessor<Message, CustomerOrder> processor() {
        return message -> {
            CustomerOrder newOrder = new CustomerOrder();
            newOrder.setOrderDescription(message.getContent());
            return newOrder;
        };
    }

    @Bean
    public ItemWriter<CustomerOrder> writer() {
        return items -> {
            items.forEach(orderRepository::save);
        };
    }
}
