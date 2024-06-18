package com.upc.saveup.card_microservice.controller;

import com.upc.saveup.card_microservice.model.CustomerCard;
import com.upc.saveup.card_microservice.repository.CustomerCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/saveup/v1")
public class CustomerCardController {
    private final CustomerCardRepository customerCardRepository;

    @Autowired
    public CustomerCardController(CustomerCardRepository customerCardRepository){
        this.customerCardRepository = customerCardRepository;
    }
    //EndPoint: localhost:8080/api/saveup/v1/customers/cards
    //Method: GET
    @Transactional(readOnly = true)
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/customers/cards")
    public ResponseEntity<List<CustomerCard>> getAllCustomerCards(){
        return new ResponseEntity<List<CustomerCard>>(customerCardRepository.findAll(), HttpStatus.OK);
    }
}