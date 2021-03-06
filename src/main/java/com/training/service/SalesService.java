package com.training.service;

import java.util.List;

import com.querydsl.core.types.Predicate;
import com.training.model.cassandra.SalesCass;
import com.training.model.jpa.Sales;

public interface SalesService {

	public List<SalesCass> getAllSales();

	public Sales addSale(Sales sales);

	public Sales updateSale(Sales sales);

	public List<Sales> getSalesByQueryDslFromJpa(Predicate predicate);
}
