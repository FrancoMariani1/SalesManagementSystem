package com.management.sales.Sales.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")

public class Product {
    private String name;
    private String description;
    private String category;
    private String brand;
    private double price;
    private Stock amount;

    public Product() {
    }

    public Product(String name, String description, String category, String brand, double price, Stock amount) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.brand = brand;
        this.price = price;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
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

    public Stock getAmount() {
        return amount;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description= description;
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

    public void setAmount(Stock amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Product [name=" + name + ", description=" + description + ", category=" + category + ", brand=" + brand + ", price=" + price + ", stock=" + amount + "]";
    }
}
