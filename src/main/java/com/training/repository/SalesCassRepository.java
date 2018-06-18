package com.training.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.training.model.Sales;

public interface SalesCassRepository extends CassandraRepository<Sales, UUID> {

}
