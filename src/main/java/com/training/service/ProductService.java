package com.training.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.training.model.cassandra.ProductCass;
import com.training.model.jpa.Product;

public interface ProductService {

	public List<ProductCass> getAllProducts();

	public ProductCass getProductByItem(int item);

	public ProductCass getProductById(UUID id);

	public Product addProduct(Product product);

	public Product updateProduct(Product product);

	public List<Product> getAllProductsFromJpa();

	public Product getProductByIdFromJpa(UUID id);
}
