package com.sg.gov.hdb.marvel.controller;


import java.util.List;
import java.util.Optional;

import com.sg.gov.hdb.marvel.model.CustomerOrder;
import com.sg.gov.hdb.marvel.model.MessageRequest;
import com.sg.gov.hdb.marvel.service.KafkaProducerService;
import com.sg.gov.hdb.marvel.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/user/{userId}")
    public ResponseEntity<CustomerOrder> addOrder(@PathVariable Long userId, @RequestBody CustomerOrder order) {
        try {
            CustomerOrder newOrder = orderService.addOrder(userId, order);
            return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{orderId}/attach/{userId}")
    public Optional<CustomerOrder> attachOrderToUser(@PathVariable Long orderId, @PathVariable Long userId) {
        return orderService.attachOrder(userId, orderId);
    }






}
