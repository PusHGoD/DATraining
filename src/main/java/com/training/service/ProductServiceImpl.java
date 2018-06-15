package com.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.model.Product;
import com.training.repository.ProductCassRepository;
import com.training.repository.ProductRepository;
import com.training.utils.DateTimeUtil;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductCassRepository cassandraRepository;

	@Autowired
	private ProductRepository jpaRepository;

	@Override
	public List<com.training.model.cassandra.ProductCassandra> getAllProducts() {
		return cassandraRepository.findAll();
	}

	@Override
	public Product addProduct(Product product) {
		product.setCreatedAt(DateTimeUtil.getCurrent());
		product.setModifiedAt(DateTimeUtil.getCurrent());
		return jpaRepository.save(product);
	}

	@Override
	public int updateProduct(Product product) {
		product.setModifiedAt(DateTimeUtil.getCurrent());
		// jpaRepository.save(product);
		jpaRepository.updateProduct(product.getItem(), product.getsClass(), product.getInventory(),
				product.getModifiedAt(), product.getProductId());
		return 1;
	}

}
