//the invoices are made for customers only. Includes the id of the invoice. Also includes the price of the product, the id,  the quantity, the total price, and the date of the purchase.

package com.management.sales.Sales.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "invoices")
public class Invoice {
    private String id;
    private String customer_id;
    private String product_id;
    private String quantity;
    private String price;
    private String total_price;
    private String date;

    public Invoice() {
    }

    public Invoice(String id, String customer_id, String product_id, String quantity, String price, String total_price,
            String date) {
        this.id = id;
        this.customer_id = customer_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.price = price;
        this.total_price = total_price;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getPrice() {
        return price;
    }

    public String getTotal_price() {
        return total_price;
    }

    public String getDate() {
        return date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Invoice [customer_id=" + customer_id + ", date=" + date + ", id=" + id + ", price=" + price
                + ", product_id=" + product_id + ", quantity=" + quantity + ", total_price=" + total_price + "]";
    }



}
