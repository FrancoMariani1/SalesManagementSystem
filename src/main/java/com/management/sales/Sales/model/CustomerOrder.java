package com.management.sales.Sales.model;

import jakarta.persistence.*;

@Entity
@Table(name = "customer_orders")

public class CustomerOrder {
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private int id;
    private Customer customer;
    private Product product;
    private int cantidad;
    private double precio;
    private double total;
    private String fecha;


    public CustomerOrder(Customer customer, Product product, int cantidad, double precio, double total,
            String fecha) {

        this.customer = customer;
        this.product = product;
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = total;
        this.fecha = fecha;
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
    public int getCantidad() {
        return cantidad;
    }
    public double getPrecio() {
        return precio;
    }
    public double getTotal() {
        return total;
    }
    public String getFecha() {
        return fecha;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public void setProducto(Product product) {
        this.product = product;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "CustomerOrder [id=" + id + "cantidad=" + cantidad + ", customer=" + customer + ", fecha=" + fecha + ", id=" + id
                + ", precio=" + precio + ", product=" + product + ", total=" + total + "]";
    }

}
