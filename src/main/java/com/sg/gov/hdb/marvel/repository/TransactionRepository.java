package com.sg.gov.hdb.marvel.repository;

import com.sg.gov.hdb.marvel.model.CustomerOrder;

import com.sg.gov.hdb.marvel.model.Transaction;
import com.sg.gov.hdb.marvel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
