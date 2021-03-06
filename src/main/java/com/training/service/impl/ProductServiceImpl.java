package com.training.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;
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
	@Transactional(readOnly = true)
	public List<ProductCass> getAllProducts() {
		return cassRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public ProductCass getProductByItem(int item) {
		return cassRepository.findOneByItem(item);
	}

	@Override
	@Transactional(readOnly = true)
	public ProductCass getProductById(UUID id) {
		ProductCass product = cassRepository.findOneByProductId(id);
		if (product == null) {
			throw new NoDataFoundException("Product ID '" + id + "' not found in DB");
		}
		return product;
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	@Transactional(readOnly = false)
	public Product addProduct(Product product) {
		product.setCreatedAt(DateTimeUtil.getCurrent());
		product.setModifiedAt(DateTimeUtil.getCurrent());
		return jpaRepository.save(product);
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	@Transactional(readOnly = false)
	public Product updateProduct(Product product) {
		if (!jpaRepository.findById(product.getProductId()).isPresent()) {
			throw new NoDataFoundException("Product ID '" + product.getProductId() + "' not found in DB");
		}
		product.setModifiedAt(DateTimeUtil.getCurrent());
		// int row = jpaRepository.setProductByProductId(product.getItem(),
		// product.getsClass(),
		// product.getInventory(), product.getModifiedAt(),
		// product.getProductId());
		return jpaRepository.save(product);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Product> getAllProductsFromJpa() {
		return jpaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Product getProductByIdFromJpa(UUID id) {
		Optional<Product> product = jpaRepository.findById(id);
		if (!product.isPresent()) {
			throw new NoDataFoundException("Product ID '" + id + "' not found in DB");
		}
		return product.get();
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteProductById(UUID id) {
		jpaRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Product> getProductByQueryDslFromJpa(Predicate predicate) {
		List<Product> list = new ArrayList<>();
		jpaRepository.findAll(predicate).forEach(list::add);
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public Product getOneProductByQueryDslFromJpa(Predicate predicate) {
		Optional<Product> result = jpaRepository.findOne(predicate);
		if (!result.isPresent()) {
			throw new NoDataFoundException("Not found data in DB");
		}
		return result.get();
	}
}
