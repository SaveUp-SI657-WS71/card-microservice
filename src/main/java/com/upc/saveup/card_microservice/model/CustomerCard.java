package com.upc.saveup.card_microservice.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer_card")
public class CustomerCard {
    @EmbeddedId
    private CustomerCardId id;

    @ManyToOne
    @MapsId("cardId")
    @JoinColumn(name = "card_id", foreignKey = @ForeignKey(name = "FK_card_customer_card"))
    private Card card;

    @Column(name = "customer_id", nullable = false)
    private int customerId;
}
