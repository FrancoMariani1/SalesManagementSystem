package com.management.sales.sales.repository.impl;

import com.management.sales.sales.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByEmail(String email);

    Optional<Customer> findById(Long id);

    Optional<Customer> findByName(String name);

    void deleteById(Long id);
}
