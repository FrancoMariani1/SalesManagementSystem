//Need to do the service for invoice

package com.management.sales.sales.service;

import com.management.sales.sales.model.Invoice;
import com.management.sales.sales.model.InvoiceProduct;
import com.management.sales.sales.model.Product;
import com.management.sales.sales.repository.impl.InvoiceProductRepository;
import com.management.sales.sales.repository.impl.InvoiceRepository;
import com.management.sales.sales.repository.impl.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

        @Autowired
        private final InvoiceRepository invoiceRepository;

        @Autowired
        private ProductRepository productRepository;

        @Autowired
        private InvoiceProductRepository invoiceProductRepository;


        @Autowired
        public InvoiceService(InvoiceRepository invoiceRepository) {
            this.invoiceRepository= invoiceRepository;}

        public List<Invoice> getAllInvoices() {
            return invoiceRepository.findAll();
        }

        public List<Invoice> getInvoiceByCustomerId(Long id) {
            return invoiceRepository.findByCustomerId(id);
        }

        public List<Invoice> getInvoiceByDate(Date date) {
            return invoiceRepository.findByDate(date);
        }

        public Optional<Invoice> getInvoiceById(Long id) {
            return invoiceRepository.findById(id);
        }

    @Transactional
    public Invoice addInvoice(Invoice invoice, List<InvoiceProduct> invoiceProducts) {
        invoiceProducts.forEach(invoiceProduct -> {
            Product product = productRepository.findById(invoiceProduct.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            invoiceProduct.setProduct(product);
            invoice.addInvoiceProduct(invoiceProduct);
        });

        double totalPrice = invoiceProducts.stream()
                .mapToDouble(ip -> ip.getQuantity() * ip.getPrice())
                .sum();
        invoice.setTotalPrice(totalPrice);

        return invoiceRepository.save(invoice);
    }

    @Transactional
    public Invoice updateInvoice(Long id, Invoice updatedInvoice, List<InvoiceProduct> updatedInvoiceProducts) {
        Invoice existingInvoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice with ID " + id + " not found!"));

        // Actualiza las propiedades básicas de la factura
        existingInvoice.setCustomer(updatedInvoice.getCustomer());
        existingInvoice.setDate(updatedInvoice.getDate());
        existingInvoice.setTotalPrice(updatedInvoice.getTotalPrice());

        // Elimina los productos antiguos
        existingInvoice.getInvoiceProducts().clear();
        invoiceProductRepository.deleteAllByInvoiceId(id);

        // Agrega los productos nuevos
        updatedInvoiceProducts.forEach(invoiceProduct -> {
            Product product = productRepository.findById(invoiceProduct.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            invoiceProduct.setInvoice(existingInvoice);
            invoiceProduct.setProduct(product);
            existingInvoice.addInvoiceProduct(invoiceProduct);
        });

        // Recalcula el totalPrice
        double totalPrice = updatedInvoiceProducts.stream()
                .mapToDouble(ip -> ip.getQuantity() * ip.getPrice())
                .sum();
        existingInvoice.setTotalPrice(totalPrice);

        return invoiceRepository.save(existingInvoice);
    }


//        public Invoice updateInvoice(Long id, Invoice updatedInvoice) {
//            if (!invoiceRepository.existsById(id)) {
//                throw new RuntimeException("Invoice with ID " + id + " not found!");
//            }
//
//            updatedInvoice.setId(id);
//
//            return invoiceRepository.save(updatedInvoice);
//        }

        @Transactional

        public void deleteInvoice(Long id) {
            invoiceRepository.deleteById(id);
        }


}
