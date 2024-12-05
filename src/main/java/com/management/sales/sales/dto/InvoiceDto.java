package com.management.sales.sales.dto;

import java.util.Date;
import java.util.Set;

public class InvoiceDto {
//    private Long id;
    private Long customerId;

    private Date date;
    private double totalPrice;
    private Set<InvoiceProductDto> invoiceProducts;

    // Getters y setters

//    public Long getId() {
//        return id;
//    }

//    public void setId(Long id) {
//        this.id = id;
//    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Set<InvoiceProductDto> getInvoiceProducts() {
        return invoiceProducts;
    }

    public void setInvoiceProducts(Set<InvoiceProductDto> invoiceProducts) {
        this.invoiceProducts = invoiceProducts;
    }
}
