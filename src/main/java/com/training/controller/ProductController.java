package com.training.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.exception.BadRequestException;
import com.training.exception.NoDataFoundException;
import com.training.model.DBType;
import com.training.model.Product;
import com.training.model.cassandra.ProductCass;
import com.training.model.dto.ProductDTO;
import com.training.service.ProductService;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

	public static final Logger log = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService service;

	@GetMapping(value = "/")
	public List<ProductDTO> getAllProducts(HttpServletResponse response) {
		List<ProductCass> list = service.getAllProducts();
		List<ProductDTO> dtoList = list.stream().map(product -> convertToDTO(product, DBType.CASSANDRA))
				.collect(Collectors.toList());
		response.addHeader("Message", "Products: " + dtoList.size());
		return dtoList;
	}

	@GetMapping(value = "")
	public ProductDTO getProductByItem(@RequestParam("item") int item) {
		ProductCass result = service.getProductByItem(item);
		return convertToDTO(result, DBType.CASSANDRA);
	}

	@PostMapping(value = "/add", headers = "Accept=application/json")
	public ProductDTO addProduct(@RequestBody ProductDTO product) {
		return convertToDTO(service.addProduct(convertToJPAEntity(product)), DBType.JPA);
	}

	@PutMapping(value = "/update", headers = "Accept=application/json")
	public ProductDTO updateProduct(@RequestBody ProductDTO product) {
		return convertToDTO(service.updateProduct(convertToJPAEntity(product)), DBType.JPA);
	}

	@DeleteMapping(value = "/delete", headers = "Accept=application/json")
	public String deleteProduct(@RequestBody Product product) {
		return "Delete success";
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
