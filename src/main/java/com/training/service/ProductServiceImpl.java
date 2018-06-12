package com.training.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.training.repository.ProductRepository;

public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository repository;
}
