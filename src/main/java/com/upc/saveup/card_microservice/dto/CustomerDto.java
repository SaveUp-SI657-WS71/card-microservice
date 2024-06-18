package com.upc.saveup.card_microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private int id;
    private String name;
    private String email;
    private String lastName;
    private int points;
}
