//service for product

package com.management.sales.Sales.service;

import com.management.sales.Sales.model.Product;
import com.management.sales.Sales.repository.impl.ProductRepository;
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

    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product updateProduct(Long id, Product product) {
        Product existingProduct = productRepository.findById(Math.toIntExact(id)).orElse(null);
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        return productRepository.save(existingProduct);
    }


}
