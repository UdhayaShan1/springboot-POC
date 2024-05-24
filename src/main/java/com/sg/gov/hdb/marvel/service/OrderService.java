package com.sg.gov.hdb.marvel.service;

import java.util.List;

import com.sg.gov.hdb.marvel.model.CustomerOrder;
import com.sg.gov.hdb.marvel.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public CustomerOrder saveOrder(CustomerOrder order) {
        return orderRepository.save(order);
    }

    public List<CustomerOrder> getAllOrders() {
        return orderRepository.findAll();
    }

    public void deleteAllOrders() {
        orderRepository.deleteAll();
    }
}
