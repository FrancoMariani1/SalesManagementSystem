//for showing the stock of all the products. I need to show the product id, name, brand, and stock.
// I will create a new class called Stock.java in the model package. The id, name and brand will be taken from the product table.

package com.management.sales.Sales.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "stocks")
public class Stock {
    private Product product;
    private int amount;
    private String date;

    public Stock() {
    }

    public Stock(Product product, int amount, String date) {
        this.product = product;
        this.amount = amount;
        this.date = date;
    }

    public Product getProduct() {
        return product;
    }

    public int getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Stock [amount=" + amount + ", date=" + date + ", product=" + product + "]";
    }

}
