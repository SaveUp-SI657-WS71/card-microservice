package com.upc.saveup.card_microservice.model;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CustomerCardId implements Serializable {
    private int customerId;
    private int cardId;
}
