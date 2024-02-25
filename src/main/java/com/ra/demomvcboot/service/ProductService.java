package com.ra.demomvcboot.service;

import com.ra.demomvcboot.model.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    Product save(Product product);
    Product findById(Long id);
    void delete(Long id);
}
