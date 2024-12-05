package com.management.sales.sales.controller;

import com.management.sales.sales.model.InvoiceProduct;
import com.management.sales.sales.service.InvoiceProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/invoiceProducts")
@CrossOrigin(origins = "localhost:3000")
public class InvoiceProductController {

    private final InvoiceProductService invoiceProductService;

    @Autowired
    public InvoiceProductController(InvoiceProductService invoiceProductService) {
        this.invoiceProductService = invoiceProductService;
    }

    @GetMapping
    public List<InvoiceProduct> getAllInvoiceProducts() {
        return invoiceProductService.getAllInvoiceProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceProduct> getInvoiceProductById(@PathVariable Long id) {
        Optional<InvoiceProduct> invoiceProduct = invoiceProductService.getInvoiceProductById(id);
        return invoiceProduct.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<InvoiceProduct> addInvoiceProduct(@RequestBody InvoiceProduct invoiceProduct) {
        InvoiceProduct createdInvoiceProduct = invoiceProductService.addInvoiceProduct(invoiceProduct);
        return new ResponseEntity<>(createdInvoiceProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvoiceProduct> updateInvoiceProduct(@PathVariable Long id, @RequestBody InvoiceProduct invoiceProduct) {
        InvoiceProduct updatedInvoiceProduct = invoiceProductService.updateInvoiceProduct(id, invoiceProduct);
        return ResponseEntity.ok(updatedInvoiceProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoiceProduct(@PathVariable Long id) {
        invoiceProductService.deleteInvoiceProduct(id);
        return ResponseEntity.noContent().build();
    }
}
