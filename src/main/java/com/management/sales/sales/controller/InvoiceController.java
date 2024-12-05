package com.management.sales.sales.controller;

import com.management.sales.sales.dto.InvoiceDto;
import com.management.sales.sales.dto.InvoiceProductDto;
import com.management.sales.sales.model.Customer;
import com.management.sales.sales.model.Invoice;
import com.management.sales.sales.model.InvoiceProduct;
import com.management.sales.sales.service.CustomerService;
import com.management.sales.sales.service.InvoiceService;
import com.management.sales.sales.service.ProductService;
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
    private final CustomerService customerService;
    private final ProductService productService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService, CustomerService customerService, ProductService productService) {
        this.invoiceService = invoiceService;
        this.customerService = customerService;
        this.productService = productService;

    }

    @GetMapping
    public List<InvoiceDto> getAllInvoices() {
        return invoiceService.getAllInvoices().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDto> getInvoiceById(@PathVariable Long id) {
        return invoiceService.getInvoiceById(id)
                .map(invoice -> ResponseEntity.ok(convertToDto(invoice)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/customer/{id}")
    public List<InvoiceDto> getInvoiceByCustomerId(@PathVariable Long id) {
        return invoiceService.getInvoiceByCustomerId(id).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/date/{date}")
    public List<InvoiceDto> getInvoiceByDate(@PathVariable @DateTimeFormat(pattern= "yyyy-MM-dd") Date date) {
        return invoiceService.getInvoiceByDate(date).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<InvoiceDto> addInvoice(@RequestBody InvoiceDto invoiceDto) {
        Invoice invoice = new Invoice();
        Customer customer = customerService.getCustomerById(invoiceDto.getCustomerId()).orElse(null);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        invoice.setCustomer(customer);
        invoice.setDate(invoiceDto.getDate());
//        invoice.setTotalPrice(invoiceDto.getTotalPrice());

        List<InvoiceProduct> invoiceProducts = invoiceDto.getInvoiceProducts().stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());

//        double totalPrice = invoiceProducts.stream()
//                .mapToDouble(ip -> ip.getQuantity() * ip.getPrice())
//                .sum();
//        invoice.setTotalPrice(totalPrice);

        Invoice createdInvoice = invoiceService.addInvoice(invoice, invoiceProducts);
        return new ResponseEntity<>(convertToDto(createdInvoice), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvoiceDto> updateInvoice(@RequestBody InvoiceDto invoiceDto, @PathVariable Long id) {
        Invoice invoice = convertToEntity(invoiceDto);
        List<InvoiceProduct> invoiceProducts = invoiceDto.getInvoiceProducts().stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
        Invoice updatedInvoice = invoiceService.updateInvoice(id, invoice);
        return ResponseEntity.ok(convertToDto(updatedInvoice));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        invoiceService.deleteInvoice(id);
        return ResponseEntity.noContent().build();
    }

    private InvoiceDto convertToDto(Invoice invoice) {
        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setCustomerId(invoice.getCustomer().getId());
        invoiceDto.setDate(invoice.getDate());
        invoiceDto.setTotalPrice(invoice.getTotalPrice());
        invoiceDto.setInvoiceProducts(invoice.getInvoiceProducts().stream()
                .map(this::convertToDto)
                .collect(Collectors.toSet()));
        return invoiceDto;
    }

    private InvoiceProductDto convertToDto(InvoiceProduct invoiceProduct) {
        InvoiceProductDto invoiceProductDto = new InvoiceProductDto();
        invoiceProductDto.setProductId(invoiceProduct.getProduct().getId());
        invoiceProductDto.setQuantity(invoiceProduct.getQuantity());
        invoiceProductDto.setPrice(invoiceProduct.getPrice());
        return invoiceProductDto;
    }

    private Invoice convertToEntity(InvoiceDto invoiceDto) {
        Invoice invoice = new Invoice();
        // Asumiendo que tienes un método en el servicio para obtener el cliente
        Customer customer = customerService.getCustomerById(invoiceDto.getCustomerId()).orElse(null);
        invoice.setCustomer(customer);
        invoice.setDate(invoiceDto.getDate());
        invoice.setTotalPrice(invoiceDto.getTotalPrice());
        return invoice;
    }

    private InvoiceProduct convertToEntity(InvoiceProductDto invoiceProductDto) {
        InvoiceProduct invoiceProduct = new InvoiceProduct();
//        Product product = productService.getProductById(invoiceProductDto.getProductId()).orElse(null);
        invoiceProduct.setProduct(productService.getProductById(invoiceProductDto.getProductId()).orElse(null));
        invoiceProduct.setQuantity(invoiceProductDto.getQuantity());
        invoiceProduct.setPrice(invoiceProductDto.getPrice());
        return invoiceProduct;
    }


}
