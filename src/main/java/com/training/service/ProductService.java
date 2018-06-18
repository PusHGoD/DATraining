package com.training.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.model.Product;

@Service
public interface ProductService {

	@Transactional(readOnly = true)
	public List<com.training.model.cassandra.ProductCass> getAllProducts();

	@Transactional(readOnly = false)
	public Product addProduct(Product product);

	@Transactional(readOnly = false)
	public Product updateProduct(Product product);
}
