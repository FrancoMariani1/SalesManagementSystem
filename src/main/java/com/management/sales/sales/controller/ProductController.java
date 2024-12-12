package com.management.sales.sales.controller;

import com.management.sales.sales.dto.ProductDto;
import com.management.sales.sales.model.Product;
import com.management.sales.sales.service.ProductService;
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
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id).orElse(null);
    }

    @PostMapping
    public Product addProduct(@RequestBody ProductDto productDto) {
        return productService.addProduct(productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(Long.valueOf(id));
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return productService.updateProduct(id, productDto);
    }
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

