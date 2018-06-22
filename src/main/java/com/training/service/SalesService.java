package com.training.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.training.model.cassandra.SalesCass;
import com.training.model.jpa.Sales;

public interface SalesService {

	@Transactional(readOnly = true)
	public List<SalesCass> getAllSales();

	@Transactional(readOnly = false)
	public Sales addSale(Sales sales);

	@Transactional(readOnly = false)
	public Sales updateSale(Sales sales);

}
