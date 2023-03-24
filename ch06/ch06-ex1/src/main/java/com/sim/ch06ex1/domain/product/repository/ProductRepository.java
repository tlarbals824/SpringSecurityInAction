package com.sim.ch06ex1.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sim.ch06ex1.domain.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
