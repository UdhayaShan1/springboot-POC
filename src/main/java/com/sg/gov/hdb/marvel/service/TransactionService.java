package com.sg.gov.hdb.marvel.service;

import com.sg.gov.hdb.marvel.model.Transaction;
import com.sg.gov.hdb.marvel.model.User;
import com.sg.gov.hdb.marvel.repository.TransactionRepository;
import com.sg.gov.hdb.marvel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserRepository userRepository;

    public Transaction getTransaction(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }
}
