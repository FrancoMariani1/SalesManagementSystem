package com.management.sales.Sales.model;

import java.util.Date;

public class Purchase {
    private int id;
    private Supplier supplier;
    private Product product;
    private int quantity;
    private double price;
    private double total;
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Purchase(int id, Supplier supplier, Product product, int quantity, double price, double total, Date date) {
        this.id = id;
        this.supplier = supplier;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Purchase [date=" + date + ", id=" + id + ", price=" + price + ", product=" + product + ", quantity="
                + quantity + ", supplier=" + supplier + ", total=" + total + "]";
    }
}
