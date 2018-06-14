package com.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.model.cassandra.Product;
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
	public com.training.model.Product addProduct(@RequestBody com.training.model.Product product) {
		return service.addProduct(product);
	}

	@PutMapping(value = "/update", headers = "Accept=application/json")
	public String updateProduct(@RequestBody Product product) {
		return null;
	}

	@DeleteMapping(value = "/delete", headers = "Accept=application/json")
	public String deleteProduct(@RequestBody Product product) {
		return null;
	}
}
