package com.leon.leandro.mscustomer.mapper;


import com.leon.leandro.mscustomer.domain.entity.Customer;
import com.leon.leandro.mscustomer.dto.CustomerRequestDto;
import com.leon.leandro.mscustomer.dto.CustomerResponseDto;

public class CustomerMapper {

    public static Customer toEntity(CustomerRequestDto dto) {
        Customer customer = new Customer();
        customer.setName(dto.name());
        customer.setGender(dto.gender());
        customer.setAge(dto.age());
        customer.setIdentification(dto.identification());
        customer.setAddress(dto.address());
        customer.setPhone(dto.phone());
        customer.setPassword(dto.password());
        customer.setActive(dto.active());
        return customer;
    }

    public static CustomerResponseDto toDto(Customer customer) {
        return new CustomerResponseDto(
                customer.getId(),
                customer.getName(),
                customer.getIdentification(),
                customer.getActive()
        );
    }
}