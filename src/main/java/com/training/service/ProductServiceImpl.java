package com.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.exception.NoDataFoundException;
import com.training.model.Product;
import com.training.model.cassandra.ProductCass;
import com.training.repository.ProductCassRepository;
import com.training.repository.ProductRepository;
import com.training.utils.DateTimeUtil;

@Service
public class ProductServiceImpl implements ProductService {

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

}
