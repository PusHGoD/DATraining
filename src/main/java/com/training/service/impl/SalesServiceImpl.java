package com.training.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;
import com.training.exception.NoDataFoundException;
import com.training.model.cassandra.SalesCass;
import com.training.model.jpa.Sales;
import com.training.repository.SalesCassRepository;
import com.training.repository.SalesRepository;
import com.training.service.BaseService;
import com.training.service.SalesService;
import com.training.utils.DateTimeUtil;

@Service
public class SalesServiceImpl extends BaseService implements SalesService {

	@Autowired
	public SalesCassRepository cassRepository;

	@Autowired
	public SalesRepository jpaRepository;

	@Override
	@Transactional(readOnly = true)
	public List<SalesCass> getAllSales() {
		return cassRepository.findAll();
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	@Transactional(readOnly = false)
	public Sales addSale(Sales sales) {
		sales.setCreatedAt(DateTimeUtil.getCurrent());
		sales.setModifiedAt(DateTimeUtil.getCurrent());
		return jpaRepository.save(sales);
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	@Transactional(readOnly = false)
	public Sales updateSale(Sales sales) {
		if (!jpaRepository.findById(sales.getId()).isPresent()) {
			throw new NoDataFoundException("Sales not found");
		}
		sales.setModifiedAt(DateTimeUtil.getCurrent());
		return jpaRepository.save(sales);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Sales> getSalesByQueryDslFromJpa(Predicate predicate) {
		List<Sales> list = new ArrayList<>();
		jpaRepository.findAll(predicate).forEach(list::add);
		return list;
	}
}
