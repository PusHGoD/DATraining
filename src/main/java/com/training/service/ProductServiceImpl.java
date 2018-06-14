package com.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.model.Product;
import com.training.repository.ProductCassandraRepository;
import com.training.repository.ProductRepository;
import com.training.utils.DateTimeUtil;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductCassandraRepository cassandraRepository;

	@Autowired
	private ProductRepository jpaRepository;

	@Override
	public List<com.training.model.cassandra.Product> getAllProducts() {
		return cassandraRepository.findAll();
	}

	@Override
	public Product addProduct(Product product) {
		product.setCreatedAt(DateTimeUtil.getCurrent());
		product.setModifiedAt(DateTimeUtil.getCurrent());
		return jpaRepository.save(product);
	}
}
