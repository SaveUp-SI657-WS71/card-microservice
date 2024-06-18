package com.upc.saveup.card_microservice.repository;

import com.upc.saveup.card_microservice.model.CustomerCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerCardRepository extends JpaRepository<CustomerCard,Integer> {
}
