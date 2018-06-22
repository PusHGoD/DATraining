package com.training.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.exception.NoDataFoundException;
import com.training.model.cassandra.ProductCass;
import com.training.model.jpa.Product;
import com.training.repository.ProductCassRepository;
import com.training.repository.ProductRepository;
import com.training.service.BaseService;
import com.training.service.ProductService;
import com.training.utils.DateTimeUtil;

@Service
public class ProductServiceImpl extends BaseService implements ProductService {

	@Autowired
	private ProductCassRepository cassRepository;

	@Autowired
	private ProductRepository jpaRepository;

	@Override
	public List<ProductCass> getAllProducts() {
		return cassRepository.findAll();
	}

	@Override
	public ProductCass getProductByItem(int item) {
		return cassRepository.findOneByItem(item);
	}

	@Override
	public ProductCass getProductById(UUID id) {
		ProductCass product = cassRepository.findOneByProductId(id);
		if (product == null) {
			throw new NoDataFoundException("Product ID '" + id + "' not found in DB");
		}
		return product;
	}

	@Override
	public Product addProduct(Product product) {
		product.setCreatedAt(DateTimeUtil.getCurrent());
		product.setModifiedAt(DateTimeUtil.getCurrent());
		return jpaRepository.save(product);
	}

	@Override
	public Product updateProduct(Product product) {
		if (!jpaRepository.findById(product.getProductId()).isPresent()) {
			throw new NoDataFoundException("Product ID '" + product.getProductId() + "' not found in DB");
		}
		product.setModifiedAt(DateTimeUtil.getCurrent());
		return jpaRepository.save(product);
	}

	@Override
	public List<Product> getAllProductsFromJpa() {
		return jpaRepository.findAll();
	}

	@Override
	public Product getProductByIdFromJpa(UUID id) {
		Optional<Product> product = jpaRepository.findById(id);
		if (!product.isPresent()) {
			throw new NoDataFoundException("Product ID '" + id + "' not found in DB");
		}
		return product.get();
	}

}
