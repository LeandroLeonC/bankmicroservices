package com.leon.leandro.mscustomer.repository;

import com.leon.leandro.mscustomer.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByIdentification(String identification);
}