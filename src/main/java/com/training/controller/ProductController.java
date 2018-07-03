package com.training.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.dto.ProductDTO;
import com.training.exception.BadRequestException;
import com.training.exception.NoDataFoundException;
import com.training.model.DBType;
import com.training.model.cassandra.ProductCass;
import com.training.model.jpa.Product;
import com.training.service.ProductService;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

	public static final Logger log = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService service;

	@GetMapping(value = "")
	public List<ProductDTO> getAllProducts(HttpServletResponse response) {
		List<ProductCass> list = service.getAllProducts();
		List<ProductDTO> dtoList = list.stream().map(product -> convertToDTO(product, DBType.CASSANDRA))
				.collect(Collectors.toList());
		response.addHeader("Message", "Product size: " + dtoList.size());
		return dtoList;
	}

	@GetMapping(value = "", params = "id")
	public ProductDTO getProductById(@RequestParam("id") UUID id, HttpServletResponse response) {
		ProductCass result = service.getProductById(id);
		response.addHeader("Location", "http://localhost:8080/product?id=" + id);
		return convertToDTO(result, DBType.CASSANDRA);
	}

	@GetMapping(value = "", params = "item")
	public ProductDTO getProductByItem(@RequestParam("item") int item, HttpServletResponse response) {
		ProductCass result = service.getProductByItem(item);
		response.addHeader("Location", "http://localhost:8080/product?id=" + result.getProductId());
		return convertToDTO(result, DBType.CASSANDRA);
	}

	@GetMapping(value = "/jpa")
	public List<ProductDTO> getAllProductsFromJpa(HttpServletResponse response) {
		List<Product> list = service.getAllProductsFromJpa();
		List<ProductDTO> dtoList = list.stream().map(product -> convertToDTO(product, DBType.JPA))
				.collect(Collectors.toList());
		response.addHeader("Message", "Product size: " + dtoList.size());
		return dtoList;
	}

	@GetMapping(value = "/jpa", params = "id")
	public ProductDTO getProductByIdFromJpa(@RequestParam("id") UUID id, HttpServletResponse response) {
		Product result = service.getProductByIdFromJpa(id);
		response.addHeader("Location", "http://localhost:8080/product?id=" + id);
		return convertToDTO(result, DBType.JPA);
	}

	@PostMapping(value = "/add", headers = "Accept=application/json")
	public ProductDTO addProduct(@RequestBody ProductDTO product, HttpServletResponse response) {
		Product result = service.addProduct(convertToJPAEntity(product));
		response.addHeader("Location", "http://localhost:8080/product?id=" + result.getProductId());
		return convertToDTO(result, DBType.JPA);
	}

	@PutMapping(value = "/update", headers = "Accept=application/json")
	public ProductDTO updateProduct(@RequestBody ProductDTO product, HttpServletResponse response) {
		response.addHeader("Location", "http://localhost:8080/product?id=" + product.getProductId());
		return convertToDTO(service.updateProduct(convertToJPAEntity(product)), DBType.JPA);
	}

	@DeleteMapping(value = "/delete", headers = "Accept=application/json")
	public ResponseEntity<Void> deleteProduct(@RequestBody UUID productId) {
		service.deleteProductById(productId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	public ProductDTO convertToDTO(Object obj, DBType type) {
		ProductDTO dto = new ProductDTO();
		if (obj == null) {
			throw new NoDataFoundException("Not found product");
		}
		if (type == DBType.JPA) {
			Product product = (Product) obj;
			dto.setProductId(product.getProductId());
			dto.setItem(product.getItem());
			dto.setsClass(product.getsClass());
			dto.setInventory(product.getInventory());
			dto.setCreatedAt(product.getCreatedAt());
			dto.setModifiedAt(product.getModifiedAt());
		} else if (type == DBType.CASSANDRA) {
			ProductCass product = (ProductCass) obj;
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
		if (dto == null) {
			throw new BadRequestException("Parameters not valid");
		}
		Product product = new Product();
		product.setProductId(dto.getProductId());
		product.setItem(dto.getItem());
		product.setsClass(dto.getsClass());
		product.setInventory(dto.getInventory());
		product.setCreatedAt(dto.getCreatedAt());
		product.setModifiedAt(dto.getModifiedAt());
		return product;
	}

	public ProductCass convertToCassandraEntity(ProductDTO dto) {
		if (dto == null) {
			throw new BadRequestException("Parameters not valid");
		}
		ProductCass product = new ProductCass();
		product.setProductId(dto.getProductId());
		product.setItem(dto.getItem());
		product.setsClass(dto.getsClass());
		product.setInventory(dto.getInventory());
		product.setCreatedAt(dto.getCreatedAt());
		product.setModifiedAt(dto.getModifiedAt());
		return product;
	}
}
