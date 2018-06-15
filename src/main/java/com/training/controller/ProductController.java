package com.training.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.exception.BadRequestException;
import com.training.model.DBType;
import com.training.model.Product;
import com.training.model.dto.ProductDTO;
import com.training.service.ProductService;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

	@Autowired
	private ProductService service;

	@GetMapping(value = "/", headers = "Accept=application/json")
	public List<ProductDTO> getAllProducts() {
		List<com.training.model.cassandra.ProductCassandra> list = service.getAllProducts();
		List<ProductDTO> dtoList = list.stream().map(product -> convertToDTO(product, DBType.CASSANDRA))
				.collect(Collectors.toList());
		return dtoList;
	}

	@PostMapping(value = "/add", headers = "Accept=application/json")
	public ProductDTO addProduct(@RequestBody ProductDTO product) {
		return convertToDTO(service.addProduct(convertToJPAEntity(product)), DBType.JPA);
	}

	@PutMapping(value = "/update", headers = "Accept=application/json")
	public String updateProduct(@RequestBody ProductDTO product) {
		service.updateProduct(convertToJPAEntity(product));
		return "Update";
	}

	// @DeleteMapping(value = "/delete", headers = "Accept=application/json")
	// public String deleteProduct(@RequestBody Product product) {
	// return null;
	// }

	public ProductDTO convertToDTO(Object obj, DBType type) {
		ProductDTO dto = new ProductDTO();
		if (type == DBType.JPA) {
			Product product = (Product) obj;
			dto.setProductId(product.getProductId());
			dto.setItem(product.getItem());
			dto.setsClass(product.getsClass());
			dto.setInventory(product.getInventory());
			dto.setCreatedAt(product.getCreatedAt());
			dto.setModifiedAt(product.getModifiedAt());
		} else if (type == DBType.CASSANDRA) {
			com.training.model.cassandra.ProductCassandra product = (com.training.model.cassandra.ProductCassandra) obj;
			dto.setProductId(product.getProductId());
			dto.setItem(product.getItem());
			dto.setsClass(product.getsClass());
			dto.setInventory(product.getInventory());
			dto.setCreatedAt(product.getCreatedAt());
			dto.setModifiedAt(product.getModifiedAt());
		} else {
			throw new BadRequestException("No type");
		}
		return dto;
	}

	public Product convertToJPAEntity(ProductDTO dto) {
		Product product = new Product();
		product.setProductId(dto.getProductId());
		product.setItem(dto.getItem());
		product.setsClass(dto.getsClass());
		product.setInventory(dto.getInventory());
		product.setCreatedAt(dto.getCreatedAt());
		product.setModifiedAt(dto.getModifiedAt());
		return product;
	}

	public com.training.model.cassandra.ProductCassandra convertToCassandraEntity(ProductDTO dto) {
		com.training.model.cassandra.ProductCassandra product = new com.training.model.cassandra.ProductCassandra();
		product.setProductId(dto.getProductId());
		product.setItem(dto.getItem());
		product.setsClass(dto.getsClass());
		product.setInventory(dto.getInventory());
		product.setCreatedAt(dto.getCreatedAt());
		product.setModifiedAt(dto.getModifiedAt());
		return product;
	}
}
