package com.management.sales.Sales.repository.impl;

import com.management.sales.Sales.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ProductRepository extends JpaRepository<Product, Integer> {
    void deleteById(Long id);
}
