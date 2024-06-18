package com.upc.saveup.card_microservice.service.impl;

import com.upc.saveup.card_microservice.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CustomerServiceImpl {
    @Autowired
    private WebClient.Builder webClientBuilder;

    public CustomerDto getCustomerById(int customerId) {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8080/api/saveup/v1/customers/" + customerId)
                .retrieve()
                .bodyToMono(CustomerDto.class)
                .block();
    }
}
