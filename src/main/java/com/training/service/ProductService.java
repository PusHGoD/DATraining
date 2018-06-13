package com.training.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.training.model.Product;

@Service
public interface ProductService {
	public List<Product> getAllProducts();

	public Product addProduct(Product product);
}
