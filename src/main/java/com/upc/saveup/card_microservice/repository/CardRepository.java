package com.upc.saveup.card_microservice.repository;

import com.upc.saveup.card_microservice.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card,Integer> {
    Card findByCardName(String cardName);
    Card findByCardNumber(String cardNumber);
    Card findByCvv(String cvv);
    Card findByCardNumberAndCustomerId(String cardNumber, int customerId);

    List<Card> findByCustomerId(int customerId);
    boolean existsByCardName(String cardName);
    boolean existsByCardNumber(String cardNumber);
    boolean existsByCardNumberAndCvv(String cardNumber, String cvv);
    boolean existsByCardNumberAndCustomerId(String cardNumber, int customerId);
    boolean existsByCvv(String cvv);
}
