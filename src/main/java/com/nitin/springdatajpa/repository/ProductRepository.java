package com.nitin.springdatajpa.repository;

import com.nitin.springdatajpa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Repository Interface
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}
