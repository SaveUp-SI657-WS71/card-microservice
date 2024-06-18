package com.upc.saveup.card_microservice.service.impl;

import com.upc.saveup.card_microservice.model.Card;
import com.upc.saveup.card_microservice.repository.CardRepository;
import com.upc.saveup.card_microservice.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    private CardRepository cardRepository;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public Card createCard(Card card) {
        return cardRepository.save(card);
    }
    @Override
    public void updateCard(Card card){
        cardRepository.save(card);
    }
    @Override
    public Card getCard(int id){
        return cardRepository.findById(id).get();
    }
    @Override
    public void deleteCard(int id){
        cardRepository.deleteById(id);
    }
    @Override
    public List<Card> getCards(){
        return (List<Card>) cardRepository.findAll();
    }

    @Override
    public boolean isCardExist(int id){
        return cardRepository.existsById(id);
    }
}
