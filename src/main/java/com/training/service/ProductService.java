package com.training.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.model.cassandra.ProductCass;
import com.training.model.jpa.Product;

@Service
public interface ProductService {

	@Transactional(readOnly = true)
	public List<ProductCass> getAllProducts();

	@Transactional(readOnly = true)
	public ProductCass getProductByItem(int item);

	@Transactional(readOnly = true)
	public ProductCass getProductById(UUID id);

	@Transactional(readOnly = false)
	public Product addProduct(Product product);

	@Transactional(readOnly = false)
	public Product updateProduct(Product product);

	@Transactional(readOnly = true)
	public List<Product> getAllJpaProducts();

	@Transactional(readOnly = true)
	public Product getJpaProductById(UUID id);
}
