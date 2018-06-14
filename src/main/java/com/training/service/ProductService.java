package com.training.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.training.model.Product;

@Service
public interface ProductService {

	public List<com.training.model.cassandra.Product> getAllProducts();

	public Product addProduct(Product product);

}
