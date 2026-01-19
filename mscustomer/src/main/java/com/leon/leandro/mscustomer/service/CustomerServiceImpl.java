package com.leon.leandro.mscustomer.service;


import com.leon.leandro.mscustomer.domain.entity.Customer;
import com.leon.leandro.mscustomer.dto.CustomerRequestDto;
import com.leon.leandro.mscustomer.dto.CustomerResponseDto;
import com.leon.leandro.mscustomer.exception.ResourceNotFoundException;
import com.leon.leandro.mscustomer.mapper.CustomerMapper;
import com.leon.leandro.mscustomer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    @Override
    public CustomerResponseDto create(CustomerRequestDto request) {
        Customer customer = CustomerMapper.toEntity(request);
        return CustomerMapper.toDto(repository.save(customer));
    }

    @Override
    public List<CustomerResponseDto> findAll() {
        return repository.findAll()
                .stream()
                .map(CustomerMapper::toDto)
                .toList();
    }

    @Override
    public CustomerResponseDto findById(Long id) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        return CustomerMapper.toDto(customer);
    }

    @Override
    public CustomerResponseDto update(Long id, CustomerRequestDto request) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        customer.setName(request.name());
        customer.setGender(request.gender());
        customer.setAge(request.age());
        customer.setAddress(request.address());
        customer.setPhone(request.phone());
        customer.setActive(request.active());

        return CustomerMapper.toDto(repository.save(customer));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Customer not found");
        }
        repository.deleteById(id);
    }
}