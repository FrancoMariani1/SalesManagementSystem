package com.management.sales.sales.repository.impl;

import com.management.sales.sales.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    Optional<Invoice> findById(Long id);

    List<Invoice> findByCustomerId(Long id);

    List<Invoice> findByDate(Date date);

    boolean existsById(Long id);

    void deleteById(Long id);
}
