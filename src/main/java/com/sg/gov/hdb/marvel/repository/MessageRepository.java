package com.sg.gov.hdb.marvel.repository;

import com.sg.gov.hdb.marvel.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
