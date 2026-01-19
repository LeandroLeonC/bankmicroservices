package com.leon.leandro.mscustomer.controller;


import com.leon.leandro.mscustomer.dto.CustomerRequestDto;
import com.leon.leandro.mscustomer.dto.CustomerResponseDto;
import com.leon.leandro.mscustomer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponseDto create(@Valid @RequestBody CustomerRequestDto request) {
        return service.create(request);
    }

    @GetMapping
    public List<CustomerResponseDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public CustomerResponseDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public CustomerResponseDto update(
            @PathVariable Long id,
            @Valid @RequestBody CustomerRequestDto request
    ) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}