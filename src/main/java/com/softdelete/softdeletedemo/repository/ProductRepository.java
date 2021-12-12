package com.softdelete.softdeletedemo.repository;

import com.softdelete.softdeletedemo.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
    
    public Iterable<Product> findByPriceLessThan(double price);
}
