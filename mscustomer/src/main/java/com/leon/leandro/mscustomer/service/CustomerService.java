package com.leon.leandro.mscustomer.service;

import com.leon.leandro.mscustomer.dto.CustomerRequestDto;
import com.leon.leandro.mscustomer.dto.CustomerResponseDto;

import java.util.List;

public interface CustomerService {
    CustomerResponseDto create(CustomerRequestDto request);
    List<CustomerResponseDto> findAll();
    CustomerResponseDto findById(Long id);
    CustomerResponseDto update(Long id, CustomerRequestDto request);
    void delete(Long id);
}
