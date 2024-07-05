package com.management.sales.Sales.service;

import com.management.sales.Sales.model.InvoiceProduct;
import com.management.sales.Sales.repository.impl.InvoiceProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceProductService {

    private final InvoiceProductRepository invoiceProductRepository;

    @Autowired
    public InvoiceProductService(InvoiceProductRepository invoiceProductRepository) {
        this.invoiceProductRepository = invoiceProductRepository;
    }

    public List<InvoiceProduct> getAllInvoiceProducts() {
        return invoiceProductRepository.findAll();
    }

    public Optional<InvoiceProduct> getInvoiceProductById(Long id) {
        return invoiceProductRepository.findById(id);
    }

    public InvoiceProduct addInvoiceProduct(InvoiceProduct invoiceProduct) {
        return invoiceProductRepository.save(invoiceProduct);
    }

    public InvoiceProduct updateInvoiceProduct(Long id, InvoiceProduct invoiceProduct) {
        InvoiceProduct existingInvoiceProduct = invoiceProductRepository.findById(id).orElseThrow(() -> new RuntimeException("InvoiceProduct not found"));
        existingInvoiceProduct.setProduct(invoiceProduct.getProduct());
        existingInvoiceProduct.setQuantity(invoiceProduct.getQuantity());
        existingInvoiceProduct.setPrice(invoiceProduct.getPrice());
        return invoiceProductRepository.save(existingInvoiceProduct);
    }

    public void deleteInvoiceProduct(Long id) {
        invoiceProductRepository.deleteById(id);
    }
}
