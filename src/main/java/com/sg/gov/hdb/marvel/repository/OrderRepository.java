package com.sg.gov.hdb.marvel.repository;

import com.sg.gov.hdb.marvel.model.CustomerOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<CustomerOrder, Long> {
}