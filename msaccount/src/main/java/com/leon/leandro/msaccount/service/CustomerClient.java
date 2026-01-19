package com.leon.leandro.msaccount.service;

import com.leon.leandro.msaccount.exception.BusinessException;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class CustomerClient {

    private final WebClient webClient;

    public CustomerClient(WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl("http://ms-customer:8080") // puerto customer
                .build();
    }

    public void validateCustomerExists(Long customerId) {
        webClient.get()
                .uri("/customers/{id}", customerId)
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError(),
                        response -> Mono.error(new BusinessException("Customer not found"))
                )
                .bodyToMono(Void.class)
                .block();
    }
}
