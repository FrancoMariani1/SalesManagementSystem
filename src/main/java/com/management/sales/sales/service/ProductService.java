//service for product

package com.management.sales.sales.service;

import com.management.sales.sales.dto.ProductDto;
import com.management.sales.sales.model.Product;
import com.management.sales.sales.repository.impl.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service

public class ProductService {

        private final ProductRepository productRepository;

        @Autowired
        public ProductService(ProductRepository productRepository) {
            this.productRepository= productRepository;}

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product addProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setCategory(productDto.getCategory());
        product.setBrand(productDto.getBrand());
        product.setPrice(productDto.getPrice());
        return productRepository.save(product);
    }

    @Transactional

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product updateProduct(Long id, ProductDto productDto) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product with ID " + id + " not found!"));

        existingProduct.setName(productDto.getName());
        existingProduct.setCategory(productDto.getCategory());
        existingProduct.setBrand(productDto.getBrand());
        existingProduct.setPrice(productDto.getPrice());

        return productRepository.save(existingProduct);
    }


//    public Product updateProduct(Long id, Product product) {
//        Product existingProduct = productRepository.findById(Math.toIntExact(id)).orElse(null);
//        existingProduct.setName(product.getName());
//        existingProduct.setPrice(product.getPrice());
//        return productRepository.save(existingProduct);
//    }


}
