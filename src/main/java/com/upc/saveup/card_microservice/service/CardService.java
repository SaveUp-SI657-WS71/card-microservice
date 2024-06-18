package com.upc.saveup.card_microservice.service;

import com.upc.saveup.card_microservice.dto.CardDto;
import com.upc.saveup.card_microservice.model.Card;

import java.util.List;

public interface CardService {
    public abstract CardDto createCard(CardDto cardDto);
    public abstract void updateCard(CardDto cardDto);
    public abstract void deleteCard(int id);
    public abstract Card getCard(int id);
    public abstract List<Card> getCards();
    public abstract boolean isCardExist(int id);
}
