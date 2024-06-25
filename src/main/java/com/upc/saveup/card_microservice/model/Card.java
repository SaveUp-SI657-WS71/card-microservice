package com.upc.saveup.card_microservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "card_name", length = 20, nullable = false)
    private String cardName;

    @Column(name = "card_number", length = 16, nullable = false)
    private String cardNumber;

    @Column(name = "cvv", length = 3, nullable = false)
    private String cvv;

    @Column(name = "type", length = 10, nullable = false)
    private String type;

    @Column(name = "customer_id", nullable = false)
    private int customerId;

}
