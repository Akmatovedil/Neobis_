package com.example.Neobis.repository;

import com.example.Neobis.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
    Product findByName(String name);
}
