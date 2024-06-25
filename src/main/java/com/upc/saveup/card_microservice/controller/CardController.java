package com.upc.saveup.card_microservice.controller;


import com.upc.saveup.card_microservice.dto.CardDto;
import com.upc.saveup.card_microservice.exception.*;
import com.upc.saveup.card_microservice.model.Card;
import com.upc.saveup.card_microservice.repository.CardRepository;
import com.upc.saveup.card_microservice.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/saveup/v1")
public class CardController {
    @Autowired
    private CardService cardService;
    private final CardRepository cardRepository;

    @Autowired
    public CardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    //EndPoint: localhost:8080/api/saveup/v1/cards
    //Method: GET
    @Transactional(readOnly = true)
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/cards")
    public ResponseEntity<List<Card>> getAllCards(){
        return new ResponseEntity<List<Card>>(cardRepository.findAll(), HttpStatus.OK);
    }

    //EndPoint: localhost:8080/api/saveup/v1/cards/customer/{id}
    //Method: GET
    @Transactional(readOnly = true)
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/cards/customer/{id}")
    public ResponseEntity<List<Card>> getCardByCustomerId(@PathVariable("id") int id){
        List<Card> cards=cardRepository.findByCustomerId(id);

        if(cards!=null){
            return new ResponseEntity<>(cards, HttpStatus.OK);
        }else{
            throw new ValidationException("Error al obtener el card");
        }
    }

    @Transactional(readOnly = true)
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/cards/card/{cardNumber}/customer/{customerId}")
    public ResponseEntity<Card> getCardByCardNumberAndCustomerId(@PathVariable("cardNumber") String cardNumber, @PathVariable("customerId") int customerId){
        Card card =cardRepository.findByCardNumberAndCustomerId(cardNumber, customerId);

        if(card!=null){
            return new ResponseEntity<>(card, HttpStatus.OK);
        }else{
            throw new ValidationException("Error al obtener el card");
        }
    }

    //EndPoint: localhost:8080/api/saveup/v1/cards
    //Method: POST
    @Transactional
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/cards")
    public ResponseEntity<CardDto> createCard(@RequestBody CardDto cardDto) {
        validateCard(cardDto);
        existsCardByCardNumberAndCVV(cardDto);
        existsCustomerByCardName(cardDto);
        existsCustomerByPCardNumber(cardDto);
        return new ResponseEntity<>(cardService.createCard(cardDto), HttpStatus.CREATED);
    }


    //EndPoint: localhost:8080/api/saveup/v1/cards/{id}
    //Method: PUT
    @Transactional
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/cards/{id}")
    public ResponseEntity<Object> updateCard(@PathVariable("id") int id,@RequestBody CardDto cardDto){
        boolean isExist=cardService.isCardExist(id);
        if(isExist){
            validateCard(cardDto);
            cardDto.setId(id);
            cardService.updateCard(cardDto);
            return new ResponseEntity<>("Card is updated succesfully", HttpStatus.OK);
        }else{
            throw new ValidationException("Error al actualizar el card");
        }
    }


    //EndPoint: localhost:8080/api/saveup/v1/cards/{id}
    //Method: DELETE
    @Transactional
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/cards/{id}")
    public ResponseEntity<Object> deleteCard(@PathVariable("id") int id){
        boolean isExist=cardService.isCardExist(id);
        if(isExist){
            cardService.deleteCard(id);
            return new ResponseEntity<>("Card is deleted successfully", HttpStatus.OK);
        }else{
            throw new ValidationException("Error al eliminar el card");
        }
    }

    private void existsCardByCardNumberAndCVV(CardDto cardDto){
        if(cardRepository.existsByCardNumberAndCvv(cardDto.getCardNumber(), cardDto.getCvv())){
            throw new ValidationException("No se puede registrar la tarjeta porque existe uno con el card name y cvv");
        }
    }
    private void existsCustomerByCardName(CardDto cardDto){
        if(cardRepository.existsByCardName(cardDto.getCardName())){
            throw new ValidationException("No se puede registrar la tarjeta porque existe uno con el card name");
        }
    }
    private void existsCustomerByPCardNumber(CardDto cardDto){
        if(cardRepository.existsByCardNumber(cardDto.getCardNumber())){
            throw new ValidationException("No se puede registrar la tarjeta porque existe uno con el card number");
        }
    }

    private void validateCard(CardDto cardDto){

        if (cardDto.getCardName()== null || cardDto.getCardName().trim().isEmpty()) {
            throw new ValidationException("El nombre de la tarjeta debe ser obligatorio");
        }

        if (cardDto.getCardNumber()== null || cardDto.getCardNumber().trim().isEmpty()) {
            throw new ValidationException("El numero de la tarjeta debe ser obligatorio");
        }

        if (cardDto.getCvv()== null || cardDto.getCvv().trim().isEmpty()) {
            throw new ValidationException("El cvv de la tarjeta debe ser obligatorio");
        }

        if(cardDto.getCardName().length()>20){
            throw new ValidationException("El nombre de la tarjeta no debe exceder los 20 caracteres");
        }
        if(cardDto.getCardNumber().length() != 16){
            throw new ValidationException("El numero de la tarjeta debe tener 16 digitos");
        }
        if(cardDto.getCvv().length() != 3){
            throw new ValidationException("El cvv de la tarjeta debe tener 3 digitos");
        }

    }
}