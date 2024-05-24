package com.integration.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.integration.model.CustomerOrder;
import com.integration.model.MessageRequest;
import com.integration.service.KafkaProducerService;
import com.integration.service.MessageService;
import com.integration.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<CustomerOrder> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("/save")
    public CustomerOrder sendOrder(@RequestBody MessageRequest messageRequest) {
        CustomerOrder tmp = new CustomerOrder();
        tmp.setOrderDescription(messageRequest.getMessage());
        return orderService.saveOrder(tmp);
    }

    @DeleteMapping()
    public void deleteAllOrders() {
        orderService.deleteAllOrders();
    }

}
