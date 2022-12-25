package com.example.crud.service;

import com.example.crud.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    Product getProductById(Long id);
    List<Product> fetchProductList();
    Product updateProduct(Product product, Long productID);
    String deleteProductById(Long productID);

}
