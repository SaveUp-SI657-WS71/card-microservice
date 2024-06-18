package com.upc.saveup.card_microservice.service.impl;

import com.upc.saveup.card_microservice.dto.CardDto;
import com.upc.saveup.card_microservice.dto.CustomerDto;
import com.upc.saveup.card_microservice.exception.ResourceNotFoundException;
import com.upc.saveup.card_microservice.model.Card;
import com.upc.saveup.card_microservice.repository.CardRepository;
import com.upc.saveup.card_microservice.service.CardService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    private CardRepository cardRepository;

    private CustomerServiceImpl customerService;

    private final ModelMapper modelMapper;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository, CustomerServiceImpl customerService, ModelMapper modelMapper) {
        this.customerService = customerService;
        this.cardRepository = cardRepository;
        this.modelMapper =  modelMapper;
    }

    @Override
    public CardDto createCard(CardDto cardDto) {
        Card card = DtoToEntity(cardDto);
        CustomerDto customer = customerService.getCustomerById(cardDto.getCustomerId());
        if(customer==null){
            throw new ResourceNotFoundException("No se encontró el cliente con id: " + cardDto.getCustomerId());
        }

        card.setCustomerId(customer.getId());

        return EntityToDto(cardRepository.save(card));
    }
    @Override
    public void updateCard(CardDto cardDto){
        Card card = DtoToEntity(cardDto);
        CustomerDto customer = customerService.getCustomerById(cardDto.getCustomerId());
        if(customer==null){
            throw new ResourceNotFoundException("No se encontró el cliente con id: " + cardDto.getCustomerId());
        }
        card.setCustomerId(customer.getId());
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

    private CardDto EntityToDto(Card card) { return modelMapper.map(card, CardDto.class); }

    private Card DtoToEntity(CardDto cardDto) {
        return modelMapper.map(cardDto, Card.class);
    }
}
