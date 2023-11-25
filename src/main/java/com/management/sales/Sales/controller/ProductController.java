package com.management.sales.Sales.controller;

import com.management.sales.Sales.model.Product;
import com.management.sales.Sales.repository.impl.ProductRepository;
import com.management.sales.Sales.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "localhost:3000")
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
        return productService.getProductById(id).orElse(null);
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(Long.valueOf(id));
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        return productService.updateProduct(Long.valueOf(id), product);
    }

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
//    @DeleteMapping("/{id}")
//    public void deleteProduct(@PathVariable Integer id) {
//        productRepository.deleteById(id);
//    }
//
//
//    @PutMapping("/{id}")
//    public Product updateProduct(@PathVariable Integer id, @RequestBody Product product) {
//        Product existingProduct = productRepository.findById(id).orElse(null);
//        existingProduct.setName(product.getName());
//        existingProduct.setPrice(product.getPrice());
//        return productRepository.save(existingProduct);
//    }
}
