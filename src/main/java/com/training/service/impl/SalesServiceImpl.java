package com.training.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.training.exception.NoDataFoundException;
import com.training.model.cassandra.SalesCass;
import com.training.model.jpa.Sales;
import com.training.repository.SalesCassRepository;
import com.training.repository.SalesRepository;
import com.training.service.BaseService;
import com.training.service.SalesService;
import com.training.utils.DateTimeUtil;

public class SalesServiceImpl extends BaseService implements SalesService {

	@Autowired
	public SalesCassRepository cassRepository;

	@Autowired
	public SalesRepository jpaRepository;

	@Override
	public List<SalesCass> getAllSales() {
		return cassRepository.findAll();
	}

	@Override
	public Sales addSale(Sales sales) {
		sales.setCreatedAt(DateTimeUtil.getCurrent());
		sales.setModifiedAt(DateTimeUtil.getCurrent());
		return jpaRepository.save(sales);
	}

	@Override
	public Sales updateSale(Sales sales) {
		if (!jpaRepository.findById(sales.getId()).isPresent()) {
			throw new NoDataFoundException("Sales not found");
		}
		sales.setModifiedAt(DateTimeUtil.getCurrent());
		return jpaRepository.save(sales);
	}

}
