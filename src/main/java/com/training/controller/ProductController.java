package com.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.model.Product;
import com.training.service.ProductService;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

	@Autowired
	private ProductService service;

	@GetMapping(value = "/", headers = "Accept=application/json")
	public List<Product> getAllProducts() {
		return service.getAllProducts();
	}

	@PostMapping(value = "/add", headers = "Accept=application/json")
	public Product addProduct(@RequestBody Product product) {
		return service.addProduct(product);
	}
}
