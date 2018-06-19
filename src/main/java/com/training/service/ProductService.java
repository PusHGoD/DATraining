package com.training.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.model.Product;
import com.training.model.cassandra.ProductCass;

@Service
public interface ProductService {

	@Transactional(readOnly = true)
	public List<ProductCass> getAllProducts();

	@Transactional(readOnly = true)
	public ProductCass getProductByItem(int item);

	@Transactional(readOnly = false)
	public Product addProduct(Product product);

	@Transactional(readOnly = false)
	public Product updateProduct(Product product);
}
