package com.management.sales.sales.repository.impl;

import com.management.sales.sales.model.InvoiceProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceProductRepository extends JpaRepository<InvoiceProduct, Long> {
  // Método para eliminar productos por ID de factura

    void deleteAllByInvoiceId(Long id);
}



//package com.management.sales.sales.repository.impl;
//
//import com.management.sales.sales.model.InvoiceProduct;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//public interface InvoiceProductRepository extends JpaRepository<InvoiceProduct, Long> {
//
//}
