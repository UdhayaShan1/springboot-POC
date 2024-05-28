package com.sg.gov.hdb.marvel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;


@Entity(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double parkingAmount;

    private char parkingType;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public double getParkingAmount() {
        return parkingAmount;
    }

    public void setParkingAmount(double parkingAmount) {
        this.parkingAmount = parkingAmount;
    }

    public char getParkingType() {
        return parkingType;
    }

    public void setParkingType(char parkingType) {
        this.parkingType = parkingType;
    }



}
