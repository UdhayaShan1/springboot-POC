package com.sg.gov.hdb.marvel.controller;

import com.sg.gov.hdb.marvel.model.CustomerOrder;
import com.sg.gov.hdb.marvel.model.MessageRequest;
import com.sg.gov.hdb.marvel.model.Transaction;
import com.sg.gov.hdb.marvel.model.User;
import com.sg.gov.hdb.marvel.service.TransactionService;
import com.sg.gov.hdb.marvel.service.UserService;
import liquibase.change.core.RenameSequenceChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.table.TableRowSorter;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Adds a new user.
     *
     * @param user the user to add
     * @return the added user
     */
    @PostMapping("/adduser")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    /**
     * Updates an existing user.
     *
     * @param id the ID of the user to update
     * @param userDetails the user details to update
     * @return a ResponseEntity containing the updated user and HTTP status code
     */
    @PutMapping("/updateuser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        return userService.getUserById(id)
                .map(user -> {
                    user.setName(userDetails.getName());
                    User updatedUser = userService.addUser(user);
                    return new ResponseEntity<>(updatedUser, HttpStatus.OK);
                })
                .orElseGet(() -> {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                });
    }

    /**
     * Deletes a user by ID.
     *
     * @param id the ID of the user to delete
     * @return a ResponseEntity with HTTP status code
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Retrieves all users.
     *
     * @return a list of all users
     */
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Finds users by name.
     *
     * @param name the name to search for
     * @return a list of users whose names contain the specified string
     */
    @GetMapping("/find")
    public List<User> getAllUsersByName(@RequestBody MessageRequest name) {
        return userService.getAllUsersByName(name.getMessage());
    }

    /**
     * Retrieves all orders for a specific user by user ID.
     *
     * @param id the ID of the user
     * @return a ResponseEntity containing the list of orders and HTTP status code
     */
    @GetMapping("/{id}/orders")
    public ResponseEntity<List<CustomerOrder>> getOrdersByUserId(@PathVariable Long id) {
        List<CustomerOrder> orders = userService.getAllOrdersFromUser(id);
        //System.out.println(orders);
        if (orders != null) {
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteall")
    public ResponseEntity<Void> deleteAllOrders() {
        userService.deleteAllUsers();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/addtransaction/{userId}")
    public ResponseEntity<User> addTransaction(@PathVariable Long userId, @RequestBody Transaction transaction) {
        return userService.saveTransaction(userId, transaction).map(user -> {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/{userId}/transactions")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable Long userId) {
        List<Transaction> listOfTransactions = userService.getAllTransactions(userId);
        if (listOfTransactions == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(listOfTransactions, HttpStatus.OK);
    }

    @PutMapping("/deletetransactions/{userId}")
    public ResponseEntity<User> removeAllTransactionsFromUser(@PathVariable Long userId) {
        return userService.removeAllTransactionsFromUser(userId)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }
}
