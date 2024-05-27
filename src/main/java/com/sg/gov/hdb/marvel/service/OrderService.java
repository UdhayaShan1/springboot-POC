package com.sg.gov.hdb.marvel.service;

import java.util.List;
import java.util.Optional;

import com.sg.gov.hdb.marvel.model.CustomerOrder;
import com.sg.gov.hdb.marvel.model.User;
import com.sg.gov.hdb.marvel.repository.OrderRepository;
import com.sg.gov.hdb.marvel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    /**
     * Saves the given order to the repository.
     *
     * @param order the order to save
     * @return the saved order
     */
    public CustomerOrder saveOrder(CustomerOrder order) {
        return orderRepository.save(order);
    }

    /**
     * Retrieves all orders from the repository.
     *
     * @return a list of all orders
     */
    public List<CustomerOrder> getAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * Deletes all orders from the repository.
     */
    public void deleteAllOrders() {
        orderRepository.deleteAll();
    }

    /**
     * Adds a new order for the specified user.
     *
     * @param userId the ID of the user
     * @param order the order to add
     * @return the added order
     * @throws RuntimeException if the user is not found
     */
    public CustomerOrder addOrder(Long userId, CustomerOrder order) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            order.setUser(user);
            return orderRepository.save(order);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    /**
     * Attaches an existing user to an existing order.
     *
     * @param userId the ID of the user
     * @param orderId the ID of the order
     * @return the updated order, or an empty Optional if either the user or order is not found
     */
    public Optional<CustomerOrder> attachOrder(Long userId, Long orderId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<CustomerOrder> orderOpt = orderRepository.findById(orderId);

        if (orderOpt.isPresent() && userOpt.isPresent()) {
            CustomerOrder order = orderOpt.get();
            User user = userOpt.get();
            order.setUser(user);
            return Optional.of(orderRepository.save(order));
        }
        return Optional.empty();
    }
}
