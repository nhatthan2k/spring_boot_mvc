package com.ra.demomvcboot.model.repository;

import com.ra.demomvcboot.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
