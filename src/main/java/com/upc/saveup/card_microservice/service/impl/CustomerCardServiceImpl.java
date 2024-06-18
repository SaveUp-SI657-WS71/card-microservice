package com.upc.saveup.card_microservice.service.impl;

import com.upc.saveup.card_microservice.dto.CustomerDto;
import com.upc.saveup.card_microservice.model.Card;
import com.upc.saveup.card_microservice.model.CustomerCard;
import com.upc.saveup.card_microservice.model.CustomerCardId;
import com.upc.saveup.card_microservice.repository.CardRepository;
import com.upc.saveup.card_microservice.repository.CustomerCardRepository;
import com.upc.saveup.card_microservice.service.CustomerCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerCardServiceImpl implements CustomerCardService {
    private CustomerCardRepository customerCardRepository;

    private CustomerServiceImpl customerServiceImpl;
    private final CardRepository cardRepository;

    @Autowired
    public CustomerCardServiceImpl(CustomerServiceImpl customerServiceImpl, CardRepository cardRepository) {
        this.customerServiceImpl = customerServiceImpl;
        this.cardRepository = cardRepository;
    }

    @Override
    public void addCardToCustomer(int customerId, int cardId) {
        CustomerDto customer = customerServiceImpl.getCustomerById(customerId);
        Card card = cardRepository.findById(cardId).orElseThrow(() -> new IllegalArgumentException("Card not found"));

        CustomerCardId customerCardId = new CustomerCardId();
        customerCardId.setCustomerId(customerId);
        customerCardId.setCardId(cardId);

        CustomerCard customerCard = new CustomerCard();
        customerCard.setId(customerCardId);
        customerCard.setCustomerId(customer.getId());
        customerCard.setCard(card);

        customerCardRepository.save(customerCard);
    }

    @Override
    public List<CustomerCard> getAllCustomerCards() {
        return (List<CustomerCard>) customerCardRepository.findAll();
    }
}
