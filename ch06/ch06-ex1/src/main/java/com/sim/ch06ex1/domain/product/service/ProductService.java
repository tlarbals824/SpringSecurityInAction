package com.sim.ch06ex1.domain.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sim.ch06ex1.domain.product.entity.Product;
import com.sim.ch06ex1.domain.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

	public List<Product> findAll(){
		return productRepository.findAll();
	}
}
