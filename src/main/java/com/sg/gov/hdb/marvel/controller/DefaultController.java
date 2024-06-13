package com.sg.gov.hdb.marvel.controller;

import com.sg.gov.hdb.marvel.model.MessageRequest;
import com.sg.gov.hdb.marvel.service.KafkaProducerService;
import com.sg.gov.hdb.marvel.service.MessageService;
import com.sg.gov.hdb.marvel.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing messages.
 */
@RestController
@RequestMapping("")
public class DefaultController {

    @PostMapping("")
    public ResponseEntity<Void> doNothing() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
