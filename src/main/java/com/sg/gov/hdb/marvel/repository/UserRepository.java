package com.sg.gov.hdb.marvel.repository;

import com.sg.gov.hdb.marvel.model.CustomerOrder;

import com.sg.gov.hdb.marvel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByNameContaining(String name);
}