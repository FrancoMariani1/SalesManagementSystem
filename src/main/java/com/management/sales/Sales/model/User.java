package com.management.sales.Sales.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String name;
    private String email;
    private String password;
    private String role;

    public User() {
    }
    public User(String name, String email, String password, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getRole() {
        return role;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setMail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", email=" + email + ", password=" + password + ", role=" + role + "]";
    }

}
