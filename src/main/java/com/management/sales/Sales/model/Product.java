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
    private String price;
    private String stock;

    public Product() {
    }

    public Product(String name, String description, String category, String brand, String price, String stock) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.brand = brand;
        this.price = price;
        this.stock = stock;
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
    public String getPrice() {
        return price;
    }
    public String getStock() {
        return stock;
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
    public void setPrice(String price) {
        this.price = price;
    }
    public void setStock(String stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Product [name=" + name + ", description=" + description + ", category=" + category + ", brand=" + brand + ", price=" + price + ", stock=" + stock + "]";
    }
}
