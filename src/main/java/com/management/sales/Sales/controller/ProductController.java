package com.management.sales.Sales.controller;

//import com.management.sales.Sales.model.Product;
//import com.management.sales.Sales.repository.impl.ProductRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RestController;
//
//@CrossOrigin(origins = "http://localhost:3000")
//@RestController
//@RequestMapping("/products")
//public class ProductController {
//
//    private final ProductRepository productRepository;
//
//    @Autowired
//    public ProductController(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }
//
//    @GetMapping
//    public List<Product> getAllProducts() {
//        return productRepository.findAll();
//    }
//
//    @GetMapping("/{id}")
//    public Product getProductById(@PathVariable Integer id) {
//        return productRepository.findById(id).orElse(null);
//    }
//
//    @PostMapping
//    public Product addProduct(@RequestBody Product product) {
//        return productRepository.save(product);
//    }
//
//    @PutMapping("/{id}")
//    public Product updateProduct(@PathVariable Integer id, @RequestBody Product updatedProduct) {
//        return productRepository.findById(id)
//                .map(product -> {
//                    product.setName(updatedProduct.getName());
//                    product.setBrand(updatedProduct.getBrand());
//                    product.setCategory(updatedProduct.getCategory());
//                    product.setPrice(updatedProduct.getPrice());
//                    return productRepository.save(product);
//                })
//                .orElseThrow(() -> new IllegalArgumentException("Product not found with id " + id));
//    }
//    @DeleteMapping("/{id}")
//    public void deleteProduct(@PathVariable Integer id) {
//        productRepository.deleteById(id);
//    }
//
//}
import com.management.sales.Sales.model.Product;
import com.management.sales.Sales.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Integer id) {
        return productService.getProductById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id " + id));
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Integer id, @RequestBody Product updatedProduct) {
        return productService.updateProduct(id, updatedProduct);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}