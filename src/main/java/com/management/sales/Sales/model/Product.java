package com.management.sales.Sales.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")

public class Product {
    @Id
    @SequenceGenerator(name = "product_sequence", sequenceName = "product_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    private Long id;

    private String name;

    private String category;
    private String brand;
    private double price;

    public Product() {
    }


    public Product(String name, String category, String brand, double price) {
        this.name = name;

        this.category = category;
        this.brand = brand;
        this.price = price;

    }

//    @ManyToMany(mappedBy = "products")
//    private List<Invoice> invoices;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<InvoiceProduct> invoiceProducts = new HashSet<>();

    public Long getId() { return id; }
    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }
    public String getBrand() {
        return brand;
    }
    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public Set<InvoiceProduct> getInvoiceProducts() {
        return invoiceProducts;
    }

    public void addInvoiceProduct(InvoiceProduct invoiceProduct) {
        this.invoiceProducts.add(invoiceProduct);
        invoiceProduct.setProduct(this);
    }

    public void removeInvoiceProduct(InvoiceProduct invoiceProduct) {
        this.invoiceProducts.remove(invoiceProduct);
        invoiceProduct.setProduct(null);
    }

    @Override
    public String toString() {
        return "Product [name=" + name + ", category=" + category + ", brand=" + brand + ", price=" + price + "]";
    }
}
