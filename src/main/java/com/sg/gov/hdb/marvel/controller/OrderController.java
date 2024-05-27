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

/**
 * REST controller for managing customer orders.
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private OrderService orderService;

    /**
     * Retrieves all customer orders.
     *
     * @return a list of all customer orders
     */
    @GetMapping
    public List<CustomerOrder> getAllOrders() {
        return orderService.getAllOrders();
    }

    /**
     * Saves a new order based on the provided message request.
     *
     * @param messageRequest the request containing the message to be saved as order description
     * @return the saved customer order
     */
    @PostMapping("/save")
    public CustomerOrder sendOrder(@RequestBody MessageRequest messageRequest) {
        CustomerOrder tmp = new CustomerOrder();
        tmp.setOrderDescription(messageRequest.getMessage());
        return orderService.saveOrder(tmp);
    }

    /**
     * Deletes all customer orders.
     */
    @DeleteMapping()
    public void deleteAllOrders() {
        orderService.deleteAllOrders();
    }

    /**
     * Adds a new order for a specific user.
     *
     * @param userId the ID of the user to whom the order will be added
     * @param order the customer order to be added
     * @return a ResponseEntity containing the added customer order and HTTP status code
     */
    @PostMapping("/user/{userId}")
    public ResponseEntity<CustomerOrder> addOrder(@PathVariable Long userId, @RequestBody CustomerOrder order) {
        try {
            CustomerOrder newOrder = orderService.addOrder(userId, order);
            return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Attaches an existing order to a specific user.
     *
     * @param orderId the ID of the order to be attached
     * @param userId the ID of the user to whom the order will be attached
     * @return an Optional containing the attached customer order if both order and user are found
     */
    @PutMapping("/{orderId}/attach/{userId}")
    public Optional<CustomerOrder> attachOrderToUser(@PathVariable Long orderId, @PathVariable Long userId) {
        return orderService.attachOrder(userId, orderId);
    }
}
