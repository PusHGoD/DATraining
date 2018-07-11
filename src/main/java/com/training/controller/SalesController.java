package com.training.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.dto.SalesDTO;
import com.training.exception.BadRequestException;
import com.training.exception.NoDataFoundException;
import com.training.model.DBType;
import com.training.model.cassandra.SalesCass;
import com.training.model.jpa.Sales;
import com.training.model.jpa.SalesId;
import com.training.service.SalesService;

@RestController
@RequestMapping("/sales")
public class SalesController {
	public static final Logger log = LoggerFactory.getLogger(SalesController.class);

	@Autowired
	private SalesService service;

	@GetMapping(value = "/", headers = "Accept=application/json")
	public List<SalesDTO> getAllSales() {
		List<SalesCass> list = service.getAllSales();
		List<SalesDTO> dtoList = list.stream().map(sales -> convertToDTO(sales, DBType.CASSANDRA))
				.collect(Collectors.toList());
		return dtoList;
	}

	@PostMapping(value = "/add", headers = "Accept=application/json")
	public ResponseEntity<SalesDTO> addSales(@RequestBody SalesDTO sales) {
		SalesDTO result = convertToDTO(service.addSale(convertToJPAEntity(sales)), DBType.JPA);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "http://localhost:8080/sales?product-id=" + sales.getProductId());
		return new ResponseEntity<SalesDTO>(result, headers, HttpStatus.CREATED);
	}

	@PutMapping(value = "/update", headers = "Accept=application/json")
	public ResponseEntity<SalesDTO> updateSales(@RequestBody SalesDTO sales) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "http://localhost:8080/sales?product-id=" + sales.getProductId());
		return new ResponseEntity<SalesDTO>(convertToDTO(service.updateSale(convertToJPAEntity(sales)), DBType.JPA),
				headers, HttpStatus.OK);
	}

	public SalesDTO convertToDTO(Object obj, DBType type) {
		SalesDTO dto = null;
		if (obj == null) {
			throw new NoDataFoundException("Not found sales");
		}
		if (type == DBType.JPA) {
			Sales sales = (Sales) obj;
			dto = new SalesDTO(sales.getId().getProductId(), sales.getId().getLocationId(), sales.getId().getTimeId(),
					sales.getDollars(), sales.getCreatedAt(), sales.getModifiedAt());
		} else if (type == DBType.CASSANDRA) {
			SalesCass sales = (SalesCass) obj;
			dto = new SalesDTO(sales.getProductId(), sales.getLocationId(), sales.getTimeId(), sales.getDollars(),
					sales.getCreatedAt(), sales.getModifiedAt());
		} else {
			throw new BadRequestException("No type");
		}
		return dto;
	}

	public Sales convertToJPAEntity(SalesDTO dto) {
		if (dto == null) {
			throw new BadRequestException("Parameters not valid");
		}
		Sales sales = new Sales();
		sales.setId(new SalesId(dto.getProductId(), dto.getTimeId(), dto.getLocationId()));
		sales.setDollars(dto.getDollars());
		sales.setCreatedAt(dto.getCreatedAt());
		sales.setModifiedAt(dto.getModifiedAt());
		return sales;
	}

	public SalesCass convertToCassandraEntity(SalesDTO dto) {
		if (dto == null) {
			throw new BadRequestException("Parameters not valid");
		}
		SalesCass sales = new SalesCass();
		sales.setProductId(dto.getProductId());
		sales.setTimeId(dto.getTimeId());
		sales.setLocationId(dto.getLocationId());
		sales.setDollars(dto.getDollars());
		sales.setCreatedAt(dto.getCreatedAt());
		sales.setModifiedAt(dto.getModifiedAt());
		return sales;
	}
}
