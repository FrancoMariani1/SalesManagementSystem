package com.management.sales.Sales.model;

import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
        private int id;
        private String name;
        private String email;
        private String phone;
        private String address;


        public Customer() {
        }
        public Customer(String name, String email, String phone, String address, String city, String state) {

            this.name = name;
            this.email = email;
            this.phone = phone;
            this.address = address;

        }


        public int getId() {
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
        public void setMail(String email) {
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
