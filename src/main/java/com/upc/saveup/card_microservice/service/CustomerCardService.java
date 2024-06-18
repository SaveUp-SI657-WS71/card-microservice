package com.upc.saveup.card_microservice.service;

import com.upc.saveup.card_microservice.model.CustomerCard;

import java.util.List;

public interface CustomerCardService {
    public abstract void addCardToCustomer(int customerId,int cardId);

    public abstract List<CustomerCard> getAllCustomerCards();
}
