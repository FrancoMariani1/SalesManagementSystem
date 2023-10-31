//Need to do the service for invoice

package com.management.sales.Sales.service;

import com.management.sales.Sales.model.Invoice;
import com.management.sales.Sales.repository.impl.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

        private final InvoiceRepository invoiceRepository;

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

        public Invoice addInvoice(Invoice invoice) {
            return invoiceRepository.save(invoice);
        }

        public Invoice updateInvoice(Long id, Invoice updatedInvoice) {
            if (!invoiceRepository.existsById(id)) {
                throw new RuntimeException("Invoice with ID " + id + " not found!");
            }

            updatedInvoice.setId(id);

            return invoiceRepository.save(updatedInvoice);
        }

        public void deleteInvoice(Long id) {
            invoiceRepository.deleteById(id);
        }


}
