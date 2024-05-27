package com.sg.gov.hdb.marvel.service;

import com.sg.gov.hdb.marvel.model.CustomerOrder;
import com.sg.gov.hdb.marvel.model.User;
import com.sg.gov.hdb.marvel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Adds a new user to the repository.
     *
     * @param user the user to add
     * @return the saved user
     */
    public User addUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Retrieves all users from the repository.
     *
     * @return a list of all users
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Retrieves all users whose names contain the specified string.
     *
     * @param name the string to search for in user names
     * @return a list of users whose names contain the specified string
     */
    public List<User> getAllUsersByName(String name) {
        return userRepository.findByNameContaining(name);
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user to retrieve
     * @return an Optional containing the found user, or an empty Optional if no user is found
     */
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to delete
     */
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * Retrieves all orders associated with a specific user.
     *
     * @param id the ID of the user whose orders are to be retrieved
     * @return a list of orders associated with the user, or null if no user is found
     */
    public List<CustomerOrder> getAllOrdersFromUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(User::getOrders).orElse(null);
    }
}
