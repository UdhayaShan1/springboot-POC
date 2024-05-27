package com.sg.gov.hdb.marvel.model;

import jakarta.persistence.*;

@Entity(name = "customer_order")
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderDescription;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)  // Allowing null values
    private User user;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderDescription;
    }

    public void setOrderDescription(String orderNumber) {
        this.orderDescription = orderNumber;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
