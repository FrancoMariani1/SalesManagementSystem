package com.management.sales.Sales.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "suppliers")
public class Supplier {
    private String name;
    private String email;
    private String phone;

    public Supplier() {
            }
    public Supplier(String name, String email, String phone, String address, String city, String state) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setMail(String email) {
        this.email = email;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Supplier [name=" + name + ", email=" + email + ", phone=" + phone + "]";
    }
}
