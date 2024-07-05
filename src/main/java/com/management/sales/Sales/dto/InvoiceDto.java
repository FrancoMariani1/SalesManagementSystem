package com.management.sales.Sales.dto;

import com.management.sales.Sales.model.Customer;

import java.util.Date;
import java.util.List;

public class InvoiceDto {
    private Customer customer;
    private double total_price;
    private Date date;
    private List<InvoiceProductDto> invoiceProducts;

    // Getters y setters
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<InvoiceProductDto> getInvoiceProducts() {
        return invoiceProducts;
    }

    public void setInvoiceProducts(List<InvoiceProductDto> invoiceProducts) {
        this.invoiceProducts = invoiceProducts;
    }
}
