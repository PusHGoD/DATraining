package com.training.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	public SalesDTO addSales(@RequestBody SalesDTO sales) {
		return convertToDTO(service.addSale(convertToJPAEntity(sales)), DBType.JPA);
	}

	@PutMapping(value = "/update", headers = "Accept=application/json")
	public SalesDTO updateSales(@RequestBody SalesDTO sales) {
		return convertToDTO(service.updateSale(convertToJPAEntity(sales)), DBType.JPA);
	}

	public SalesDTO convertToDTO(Object obj, DBType type) {
		SalesDTO dto = new SalesDTO();
		if (obj == null) {
			throw new NoDataFoundException("Not found sales");
		}
		if (type == DBType.JPA) {
			Sales sales = (Sales) obj;
			dto.setProductId(sales.getId().getProductId());
			dto.setTimeId(sales.getId().getTimeId());
			dto.setLocationId(sales.getId().getLocationId());
			dto.setDollars(sales.getDollars());
			dto.setCreatedAt(sales.getCreatedAt());
			dto.setModifiedAt(sales.getModifiedAt());
		} else if (type == DBType.CASSANDRA) {
			SalesCass sales = (SalesCass) obj;
			dto.setProductId(sales.getProductId());
			dto.setTimeId(sales.getTimeId());
			dto.setLocationId(sales.getLocationId());
			dto.setDollars(sales.getDollars());
			dto.setCreatedAt(sales.getCreatedAt());
			dto.setModifiedAt(sales.getModifiedAt());
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
