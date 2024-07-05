package com.management.sales.Sales.controller;

import com.management.sales.Sales.dto.InvoiceDto;
import com.management.sales.Sales.model.Invoice;
import com.management.sales.Sales.model.InvoiceProduct;
import com.management.sales.Sales.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/invoices")
@CrossOrigin(origins = "localhost:3000")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public List<Invoice> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long id) {
        return invoiceService.getInvoiceById(id)
                .map(invoice -> ResponseEntity.ok(invoice))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/customer/{id}")
    public List<Invoice> getInvoiceByCustomerId(@PathVariable Long id) {
        return invoiceService.getInvoiceByCustomerId(id);
    }

    @GetMapping("/date/{date}")
    public List<Invoice> getInvoiceByDate(@PathVariable @DateTimeFormat(pattern= "yyyy-MM-dd") Date date) {
        return invoiceService.getInvoiceByDate(date);
    }

    @PostMapping
    public ResponseEntity<Invoice> addInvoice(@RequestBody InvoiceDto invoiceDto) {
        Invoice invoice = new Invoice();
        invoice.setCustomer(invoiceDto.getCustomer());
        invoice.setDate(invoiceDto.getDate());
        invoice.setTotalPrice(invoiceDto.getTotal_price());

        List<InvoiceProduct> invoiceProducts = invoiceDto.getInvoiceProducts().stream()
                .map(dto -> {
                    InvoiceProduct invoiceProduct = new InvoiceProduct();
                    invoiceProduct.setProduct(dto.getProduct());
                    invoiceProduct.setQuantity(dto.getQuantity());
                    invoiceProduct.setPrice(dto.getPrice());
                    return invoiceProduct;
                }).collect(Collectors.toList());

        Invoice createdInvoice = invoiceService.addInvoice(invoice, invoiceProducts);
        return new ResponseEntity<>(createdInvoice, HttpStatus.CREATED);
    }

//    @PostMapping
//    public ResponseEntity<Invoice> addInvoice(@RequestBody Invoice invoice) {
//        Invoice createdInvoice = invoiceService.addInvoice(invoice);
//        return new ResponseEntity<>(createdInvoice, HttpStatus.CREATED);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<Invoice> updateInvoice(@RequestBody Invoice invoice, @PathVariable Long id) {
        return ResponseEntity.ok(invoiceService.updateInvoice(id, invoice));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        invoiceService.deleteInvoice(id);
        return ResponseEntity.noContent().build();
    }


}
