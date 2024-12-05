//I want to create a sales management system. As entities, I will have users, customers, products, suppliers, purchases, sales, invoices, orders, and inventory.

package com.management.sales.sales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SalesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesApplication.class, args);
	}

}
