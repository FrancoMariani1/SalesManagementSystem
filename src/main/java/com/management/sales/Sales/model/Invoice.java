//the invoices are made for customers only. Includes the id of the invoice. Also includes the price of the product, the id,  the quantity, the total price, and the date of the purchase.

package com.management.sales.Sales.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    @SequenceGenerator(name = "invoice_sequence", sequenceName = "invoice_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoice_sequence")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    private Date date;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<InvoiceProduct> invoiceProducts = new HashSet<>();

    private double totalPrice;

    public Invoice() {
    }

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setInvoiceProducts(Set<InvoiceProduct> invoiceProducts) {
        this.invoiceProducts = invoiceProducts;
    }

    public Set<InvoiceProduct> getInvoiceProducts() {
        return invoiceProducts;
    }

    public void addInvoiceProduct(InvoiceProduct invoiceProduct) {
        this.invoiceProducts.add(invoiceProduct);
        invoiceProduct.setInvoice(this);
    }

    public void removeInvoiceProduct(InvoiceProduct invoiceProduct) {
        this.invoiceProducts.remove(invoiceProduct);
        invoiceProduct.setInvoice(null);
    }

    @Override
    public String toString() {
        return "Invoice [id=" + id + ", date=" + date + ", customer=" + customer + "]";
    }
}






