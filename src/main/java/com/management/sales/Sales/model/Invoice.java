//the invoices are made for customers only. Includes the id of the invoice. Also includes the price of the product, the id,  the quantity, the total price, and the date of the purchase.

package com.management.sales.Sales.model;

import jakarta.persistence.*;

@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private int id;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
    private String quantity;
    private String price;
    private String total_price;
    private String date;

    public Invoice() {
    }

    public Invoice(Customer customer, Product product, String quantity, String price, String total_price,
            String date) {

        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.total_price = total_price;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Product getProduct() {
        return product;
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



    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setProduct(Product product) {
        this.product = product;
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
        return "Invoice [id=" + id + ", date=" + date + ", id=" + id + ", price=" + price
                + ", quantity=" + quantity + ", total_price=" + total_price + "]";
    }



}
