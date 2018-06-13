package com.training.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.model.Product;
import com.training.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository repository;

	@Override
	public List<Product> getAllProducts() {
		return repository.findAll();
	}

	@Override
	public Product addProduct(Product product) {
		product.setCreatedAt(LocalDateTime.now());
		product.setModifiedAt(LocalDateTime.now());
		return repository.save(product);
	}
}
