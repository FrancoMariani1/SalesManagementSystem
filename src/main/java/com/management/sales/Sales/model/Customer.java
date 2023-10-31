package com.management.sales.Sales.model;

import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @SequenceGenerator(name = "customer_sequence", sequenceName = "customer_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_sequence")
        private Long id;
        private String name;
        private String email;
        private String phone;
        private String address;


        public Customer() {
        }
        public Customer(String name, String email, String phone, String address) {

            this.name = name;
            this.email = email;
            this.phone = phone;
            this.address = address;

        }


        public Long getId() {
            return id;
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
        public String getAddress() {
            return address;
        }


        public void setName(String name) {
            this.name = name;
        }
        public void setEmail(String email) {
            this.email = email;
        }
        public void setPhone(String phone) {
            this.phone = phone;
        }
        public void setAddress(String address) {
            this.address = address;
        }


        @Override
        public String toString() {
            return "Customer [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", address=" + address + "]";
        }


}
