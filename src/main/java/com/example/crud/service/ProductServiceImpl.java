package com.example.crud.service;

import com.example.crud.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product getProductById(Long id) {
        return null;
    }

    @Override
    public List<Product> fetchProductList() {
        return null;
    }

    @Override
    public Product updateProduct(Product product, Long productID) {
        return null;
    }

    @Override
    public String deleteProductById(Long productID) {
        return null;
    }
}
